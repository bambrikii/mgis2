package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sovzond.mgis2.integration.HibernateConfiguration;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class LandsImporterTest {

	@Autowired
	private LandsImporter landsImporter;

	@Test
	public void testImp() throws IOException {
		try (InputStream inputStream = LandsImporterTest.class.getResourceAsStream("doc2394077.xml")) {
			landsImporter.imp(inputStream);
		}
	}
}
