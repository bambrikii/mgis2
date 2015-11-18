package ru.sovzond.mgis2.web.data_exchange.rusregister;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sovzond.mgis2.integration.data_exchange.imp.Importable;
import ru.sovzond.mgis2.integration.data_exchange.imp.LandsImporter;
import ru.sovzond.mgis2.web.data_exchange.imp.AbstractUploadServlet;

import javax.servlet.annotation.MultipartConfig;
import java.io.InputStream;

/**
 * Created by Alexander Arakelyan on 17.11.15.
 */
@MultipartConfig
public class LandsImportServlet extends AbstractUploadServlet {

	@Autowired
	private LandsImporter landsImporter;

	protected void doImport(InputStream inputStream) {
		Importable importable = new LandsImporter();
		importable.imp(inputStream);
	}
}
