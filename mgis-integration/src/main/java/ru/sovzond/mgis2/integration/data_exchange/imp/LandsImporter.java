package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.sovzond.mgis2.integration.data_exchange.imp.handlers.Region_CadastrHandler;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportRecord;

import java.io.*;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
@Service
public class LandsImporter implements Importable {

	private static final Logger logger = LoggerFactory.getLogger(LandsImporter.class);

	@Autowired
	private LandImportResolverBean landImportResolverBean;

	public List<ReportRecord> imp(File file) {
		try (InputStream is = new FileInputStream(file)) {
			return imp(is);
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException(ex);
		} catch (IOException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public List<ReportRecord> imp(InputStream inputStream) {
		Parser parser = new Parser();
		LandResolver landResolver = new LandResolver(landImportResolverBean);
		DefaultHandler handler = new Region_CadastrHandler(landResolver);
		try {
			parser.parse(inputStream, handler);
		} catch (SAXException ex) {
			logger.error(ex.getMessage(), ex);
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
		return landResolver.getReports();
	}
}
