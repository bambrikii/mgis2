package ru.sovzond.mgis2.web.data_exchange.rusregister;

import ru.sovzond.mgis2.web.data_exchange.imp.AbstractUploadServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by Alexander Arakelyan on 17.11.15.
 */
public class LandsImportServlet extends AbstractUploadServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {
			out.println("aaa " + req.getRequestedSessionId() + ", ");
			try (ServletInputStream is = req.getInputStream()) {
				byte[] bytes = new byte[1024];
				int l = -1;
				while ((l = is.read(bytes)) != -1) {
					out.println("l: " + l);
				}
			}
		}
		OutputStream os = new ByteArrayOutputStream();
		consume(req, resp, os);
	}
}
