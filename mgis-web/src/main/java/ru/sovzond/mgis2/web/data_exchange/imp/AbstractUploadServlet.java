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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flowChunkNumber = getFlowChunkNumber(request);

		FlowInfo info = getFlowInfo(request);

		try (RandomAccessFile raf = new RandomAccessFile(info.flowFilePath, "rw")) {

			//Seek to position
			raf.seek((flowChunkNumber - 1) * info.flowChunkSize);

			//Save to file
			Part part = request.getPart("file");

			try (InputStream is = part.getInputStream()) {
				long read = 0;
				long contentLength = request.getContentLength();
				byte[] bytes = new byte[1024 * 100];
				while (read < contentLength) {
					int r = is.read(bytes);
					if (r < 0) {
						break;
					}
					raf.write(bytes, 0, r);
					read += r;
				}
			}


			//Mark as uploaded.
			info.uploadedChunks.add(new FlowInfo.ResumableChunkNumber(flowChunkNumber));
			if (info.checkIfUploadFinished()) { //Check if all chunks uploaded, and change filename
				FlowInfoStorage.getInstance().remove(info);
				response.getWriter().print("All finished.");
				File file = new File(info.flowFilePath);
				try (InputStream is = new FileInputStream(file)) {
					doImport(is);
				} finally {
					Files.delete(file.toPath());
				}
			} else {
				response.getWriter().print("Upload");
			}
		}
	}

	protected abstract void doImport(InputStream inputStream);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flowChunkNumber = getFlowChunkNumber(request);

		FlowInfo info = getFlowInfo(request);

		if (info.uploadedChunks.contains(new FlowInfo.ResumableChunkNumber(flowChunkNumber))) {
			response.getWriter().print("Uploaded."); //This Chunk has been Uploaded.
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private int getFlowChunkNumber(HttpServletRequest request) {
		return HttpUtils.toInt(request.getParameter("flowChunkNumber"), -1);
	}

	private FlowInfo getFlowInfo(HttpServletRequest request) throws ServletException {
		String base_dir = UPLOAD_DIR;

		int chunkSize = HttpUtils.toInt(request.getParameter("flowChunkSize"), -1);
		long totalSize = HttpUtils.toLong(request.getParameter("flowTotalSize"), -1);
		String identifier = request.getParameter("flowIdentifier");
		String filename = request.getParameter("flowFilename");
		String relativePath = request.getParameter("flowRelativePath");
		//Here we add a ".temp" to every upload file to indicate NON-FINISHED
		String filePath = new File(base_dir, filename).getAbsolutePath() + ".temp";

		FlowInfoStorage storage = FlowInfoStorage.getInstance();

		FlowInfo info = storage.getFlowInfo(chunkSize, totalSize,
				identifier, filename, relativePath, filePath);
		if (!info.isVaild()) {
			storage.remove(info);
			throw new ServletException("Invalid request params.");
		}
		return info;
	}
}
