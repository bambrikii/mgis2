package ru.sovzond.mgis2.web.data_exchange.imp;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */

import org.springframework.web.multipart.MultipartFile;
import ru.sovzond.mgis2.common.exceptions.StackTraceFactory;

import java.io.*;
import java.nio.file.Files;

/**
 * This is a servlet demo,  for using Resumable.js to upload files.
 * <p>
 * by fanxu123
 */
public abstract class UploadControllerBase {

	public static final String UPLOAD_DIR = System.getProperty("java.io.tmpdir");
	public static final String FLOW_CHUNK_NUMBER = "flowChunkNumber";
	public static final String FLOW_CHUNK_SIZE = "flowChunkSize";
	public static final String FLOW_TOTAL_SIZE = "flowTotalSize";
	public static final String FLOW_IDENTIFIER = "flowIdentifier";
	public static final String FLOW_FILENAME = "flowFilename";
	public static final String FLOW_RELATIVE_PATH = "flowRelativePath";
	public static final String FLOW_FILE = "file";

	protected FlowInfo writeFlowInfo(int chunkSize, long totalSize, String identifier, String filename, String relativePath) {
		//Here we add a ".temp" to every upload file to indicate NON-FINISHED
		String filePath = new File(UPLOAD_DIR, filename + "." + identifier).getAbsolutePath() + ".temp";

		FlowInfoStorage storage = FlowInfoStorage.getInstance();

		FlowInfo info = storage.writeFlowInfoIfNone(chunkSize, totalSize, identifier, filename, relativePath, filePath);
		if (!info.isValid()) {
			storage.remove(info);
			throw new IllegalArgumentException("Invalid request params.");
		}
		return info;
	}

	protected String processStream(int flowChunkNumber, FlowInfo info, MultipartFile part, ImportProcessable importProcessable) {
		StringBuilder result = new StringBuilder();
		try {
			try (RandomAccessFile raf = new RandomAccessFile(info.getFlowFilePath(), "rw")) {

				//Seek to position
				long pos = (long) (flowChunkNumber - 1) * info.getFlowChunkSize();
				raf.seek(pos);

				long contentLength = part.getSize();

				long bytesReadTotal = 0;
				try (InputStream is = part.getInputStream()) {
					byte[] bytes = new byte[1024 * 100];
					while (bytesReadTotal < contentLength) {
						int r = is.read(bytes);
						if (r < 0) {
							break;
						}
						raf.write(bytes, 0, r);
						bytesReadTotal += r;
					}
				}

				//Mark as uploaded.
				info.addChunk(flowChunkNumber, pos, pos + bytesReadTotal);
				if (info.checkIfUploadComplete()) {
					//Check if all chunks uploaded, and change filename
					FlowInfoStorage.getInstance().remove(info);
					result.append("All finished.");
					File file = new File(info.getFlowFilePath());
					try (InputStream is = new FileInputStream(file)) {
						result.append(importProcessable.doImport(is));
					} catch (Exception ex) {
						StackTraceFactory.printStackTrace(result, ex);
					} finally {
						// Upload finished, delete the file.
						Files.delete(file.toPath());
					}
				} else {
					result.append("Upload: chunkNumber:").append(flowChunkNumber).append(", chunkSize:").append(info.getFlowChunkSize()).append(", seekPosition:").append(pos).append(", contentLength:").append(contentLength).append(", totalSize:").append(info.getFlowTotalSize()).append(" .");
				}
			}
		} catch (IOException ex) {
			StackTraceFactory.printStackTrace(result, ex);
		}
		return result.toString();
	}

	protected FlowInfo readFlowInfo(String identifier) {
		FlowInfoStorage storage = FlowInfoStorage.getInstance();
		FlowInfo info = storage.readFlowInfo(identifier);
		return info;
	}
}
