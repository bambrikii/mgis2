package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.sovzond.mgis2.integration.data_exchange.imp.handlers.Region_CadastrHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
@Component
public class LandsImporter implements Importable {

	private static final Logger logger = LoggerFactory.getLogger(LandsImporter.class);

	@Autowired
	private LandImportResolverBean landImportResolverBean;

	public void imp(File file) {
		try (InputStream is = new FileInputStream(file)) {
			imp(is);
		} catch (FileNotFoundException ex) {
			logger.error(ex.getMessage(), ex);
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	public void imp(InputStream inputStream) {
		Parser parser = new Parser();
		List<Long> ids = new ArrayList<>();
		DefaultHandler handler = new Region_CadastrHandler(new UpdatableCoordinateSystemResolver(landImportResolverBean));
		try {
			parser.parse(inputStream, handler);
		} catch (SAXException ex) {
			logger.error(ex.getMessage(), ex);
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
}
