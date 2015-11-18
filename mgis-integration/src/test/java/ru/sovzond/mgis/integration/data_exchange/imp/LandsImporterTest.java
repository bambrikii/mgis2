package ru.sovzond.mgis.integration.data_exchange.imp;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
public class LandsImporterTest {
	@Test
	public void testImp() throws IOException {
		Importable importable = new LandsImporter();

		try (InputStream inputStream = LandsImporterTest.class.getResourceAsStream("doc2394077.xml")) {
			importable.imp(inputStream);
		}
	}
}
