package ru.sovzond.mgis2.integration.data_exchange.imp.handlers;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Alexander Arakelyan on 02.12.15.
 */
public class PropertyParserTest {
	@Test
	public void testPropertyNames() {
		Properties prop = new Properties();
		try (InputStream in = getClass().getResourceAsStream(Region_CadastrHandler.class.getSimpleName() + ".properties")) {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration<?> propertyNames = prop.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String name = (String) propertyNames.nextElement();
			Assert.assertNotNull(name);
			String value = prop.getProperty(name);
			Assert.assertNotNull(value);
			System.out.println(name + "=" + value);
		}

	}
}
