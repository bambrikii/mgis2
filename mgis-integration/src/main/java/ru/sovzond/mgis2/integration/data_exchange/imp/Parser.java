package ru.sovzond.mgis2.integration.data_exchange.imp;

/**
 * Created by donchenko-y on 11/18/15.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Parser {
	private static final Logger logger = LoggerFactory.getLogger(LandsImporter.class);

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser parser = null;

	public Parser() {
		try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void parse(File xmlFile, DefaultHandler handler) throws SAXException, IOException {
		parser.parse(xmlFile, handler);
	}

	public void parse(InputStream inputStream, DefaultHandler handler) throws SAXException, IOException {
		parser.parse(inputStream, handler);
	}


}
