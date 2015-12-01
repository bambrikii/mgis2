package ru.sovzond.mgis2.integration.data_exchange.imp.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.sovzond.mgis2.integration.data_exchange.imp.Importable;
import ru.sovzond.mgis2.integration.data_exchange.imp.Parser;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportRecord;

import java.io.*;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 01/12/15.
 */
abstract class LandImportBase implements Importable {

	private static final Logger logger = LoggerFactory.getLogger(LandImportBase.class);

	public List<ReportRecord> imp(File file) {
		return doImport(file);
	}

	private List<ReportRecord> doImport(File file) {
		try (InputStream is = new FileInputStream(file)) {
			return imp(is);
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException(ex);
		} catch (IOException ex) {
			throw new IllegalArgumentException(ex);
		}
	}


	void doImport(InputStream inputStream, DefaultHandler handler) {
		Parser parser = new Parser();
		try {
			parser.parse(inputStream, handler);
		} catch (SAXException ex) {
			logger.error(ex.getMessage(), ex);
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
}
