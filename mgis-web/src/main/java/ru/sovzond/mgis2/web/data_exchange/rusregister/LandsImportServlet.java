package ru.sovzond.mgis2.web.data_exchange.rusregister;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sovzond.mgis2.integration.data_exchange.imp.LandsImporter;
import ru.sovzond.mgis2.lands.LandBean;
import ru.sovzond.mgis2.web.data_exchange.imp.AbstractUploadServlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import java.io.InputStream;

/**
 * Created by Alexander Arakelyan on 17.11.15.
 */
@WebServlet(name = "kpt-to-lands-import-servlet", urlPatterns = "/data-exchange/import/rusregister/lands")
@MultipartConfig
public class LandsImportServlet extends AbstractUploadServlet {

	@Autowired
	private LandsImporter landsImporter;

	@Autowired
	private LandBean landBean;

	protected void doImport(InputStream inputStream) {
		landsImporter.imp(inputStream);
	}
}
