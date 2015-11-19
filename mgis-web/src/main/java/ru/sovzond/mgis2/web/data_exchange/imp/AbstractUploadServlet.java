package ru.sovzond.mgis2.web.data_exchange.imp;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;

/**
 * This is a servlet demo,  for using Resumable.js to upload files.
 * <p>
 * by fanxu123
 */
@MultipartConfig
public abstract class AbstractUploadServlet extends HttpServlet {

	public static final String UPLOAD_DIR = System.getProperty("java.io.tmpdir");
	public static final String FLOW_CHUNK_NUMBER = "flowChunkNumber";
	public static final String FLOW_CHUNK_SIZE = "flowChunkSize";
	public static final String FLOW_TOTAL_SIZE = "flowTotalSize";
	public static final String FLOW_IDENTIFIER = "flowIdentifier";
	public static final String FLOW_FILENAME = "flowFilename";
	public static final String FLOW_RELATIVE_PATH = "flowRelativePath";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flowChunkNumber = getFlowChunkNumber(request);
		FlowInfo info = writeFlowInfo(request);

		try (RandomAccessFile raf = new RandomAccessFile(info.getFlowFilePath(), "rw")) {

			//Seek to position
			long pos = (long) (flowChunkNumber - 1) * info.getFlowChunkSize();
			raf.seek(pos);

			long contentLength = request.getContentLength();

			//Save to file
			Part part = request.getPart("file");

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
			if (info.checkIfUploadComplete()) { //Check if all chunks uploaded, and change filename
				FlowInfoStorage.getInstance().remove(info);
				response.getWriter().print("All finished.");
				File file = new File(info.getFlowFilePath());
				try (InputStream is = new FileInputStream(file)) {
					doImport(is);
				} catch (Exception ex) {
					ex.printStackTrace(response.getWriter());
				} finally {
					// Upload finished, change filename.
					Files.delete(file.toPath());
				}
			} else {
				response.getWriter().print(
						"Upload: chunkNumber:" + flowChunkNumber
								+ ", chunkSize:" + info.getFlowChunkSize()
								+ ", seekPosition:" + pos
								+ ", contentLength:" + contentLength
								+ ", totalSize:" + info.getFlowTotalSize()
								+ " ."
				);
			}
		}
	}

	protected abstract void doImport(InputStream inputStream);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flowChunkNumber = getFlowChunkNumber(request);

		FlowInfo info = readFlowInfo(request);

		if (info != null && info.containsChunk(flowChunkNumber)) {
			response.getWriter().print("Uploaded."); //This Chunk has been Uploaded.
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private int getFlowChunkNumber(HttpServletRequest request) {
		return HttpUtils.toInt(request.getParameter(FLOW_CHUNK_NUMBER), -1);
	}

	private FlowInfo writeFlowInfo(HttpServletRequest request) throws ServletException {
		int chunkSize = HttpUtils.toInt(request.getParameter(FLOW_CHUNK_SIZE), -1);
		long totalSize = HttpUtils.toLong(request.getParameter(FLOW_TOTAL_SIZE), -1);
		String identifier = request.getParameter(FLOW_IDENTIFIER);
		String filename = request.getParameter(FLOW_FILENAME);
		String relativePath = request.getParameter(FLOW_RELATIVE_PATH);
		//Here we add a ".temp" to every upload file to indicate NON-FINISHED
		String filePath = new File(UPLOAD_DIR, filename + "." + identifier).getAbsolutePath() + ".temp";

		FlowInfoStorage storage = FlowInfoStorage.getInstance();

		FlowInfo info = storage.writeFlowInfoIfNone(chunkSize, totalSize, identifier, filename, relativePath, filePath);
		if (!info.isValid()) {
			storage.remove(info);
			throw new ServletException("Invalid request params.");
		}
		return info;
	}

	private FlowInfo readFlowInfo(HttpServletRequest request) {
		FlowInfoStorage storage = FlowInfoStorage.getInstance();
		String identifier = request.getParameter(FLOW_IDENTIFIER);
		FlowInfo info = storage.readFlowInfo(identifier);
		return info;
	}
}
