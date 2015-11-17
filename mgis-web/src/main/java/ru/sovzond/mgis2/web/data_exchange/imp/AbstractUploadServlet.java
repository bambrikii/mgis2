package ru.sovzond.mgis2.web.data_exchange.imp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Alexander Arakelyan on 17.11.15.
 * <p>
 * https://github.com/flowjs/flow.js/tree/master/samples/java/src/resumable/js/upload
 */
public class AbstractUploadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		OutputStream os = new ByteArrayOutputStream();
		consume(request, response, os);
	}

	protected void consume(HttpServletRequest request, HttpServletResponse response, OutputStream os) throws ServletException, IOException {
		int resumableChunkNumber = getResumableChunkNumber(request);

		ResumableInfo info = getResumableInfo(request);

		InputStream is = request.getInputStream();
		long readed = 0;
		long content_length = request.getContentLength();
		byte[] bytes = new byte[1024 * 100];
		while (readed < content_length) {
			int r = is.read(bytes);
			if (r < 0) {
				break;
			}
			os.write(bytes, 0, r);
			readed += r;
		}
		os.close();

		info.uploadedChunks.add(new ResumableInfo.ResumableChunkNumber(resumableChunkNumber));
		if (info.checkIfUploadFinished()) {
			ResumableInfoStorage.getInstance().remove(info);
			response.getWriter().print("All finished.");
		} else {
			response.getWriter().print("Upload");
		}
	}

	private int getResumableChunkNumber(HttpServletRequest request) {
		return HttpUtils.toInt(request.getParameter("resumableChunkNumber"), -1);
	}

	private ResumableInfo getResumableInfo(HttpServletRequest request) throws ServletException {

		int resumableChunkSize = HttpUtils.toInt(request.getParameter("resumableChunkSize"), -1);
		long resumableTotalSize = HttpUtils.toLong(request.getParameter("resumableTotalSize"), -1);
		String resumableIdentifier = request.getParameter("resumableIdentifier");
		String resumableFilename = request.getParameter("resumableFilename");
		String resumableRelativePath = request.getParameter("resumableRelativePath");

		ResumableInfoStorage storage = ResumableInfoStorage.getInstance();

		ResumableInfo info = storage.get(resumableChunkSize, resumableTotalSize,
				resumableIdentifier, resumableFilename, resumableRelativePath, "resumableFilePath");
		if (!info.vaild()) {
			storage.remove(info);
			throw new ServletException("Invalid request params.");
		}
		return info;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int resumableChunkNumber = getResumableChunkNumber(request);

		ResumableInfo info = getResumableInfo(request);

		if (info.uploadedChunks.contains(new ResumableInfo.ResumableChunkNumber(resumableChunkNumber))) {
			response.getWriter().print("Uploaded.");
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
