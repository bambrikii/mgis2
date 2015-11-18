package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.sovzond.mgis2.integration.data_exchange.imp.handlers.Region_CadastrHandler;
import ru.sovzond.mgis2.lands.LandBean;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
@Component
public class LandsImporter implements Importable {
	@Autowired
	private LandBean landBean;

	public void imp(File file) {
		try (InputStream is = new FileInputStream(file)) {
			imp(is);
		} catch (FileNotFoundException e) {
			// TODO:
			e.printStackTrace();
		} catch (IOException e) {
			// TODO:
			e.printStackTrace();
		}
	}

	public void imp(InputStream inputStream) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		DefaultHandler handler = new Region_CadastrHandler();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(inputStream, handler);
		} catch (ParserConfigurationException e) {
			// TODO:
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO:
			e.printStackTrace();
		} catch (IOException e) {
			// TODO:
			e.printStackTrace();
		}
		throw new UnsupportedOperationException("Not yet implemented.");
	}
}
