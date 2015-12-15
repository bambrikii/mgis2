package ru.sovzond.mgis2.integration.data_exchange.imp.impl;

import org.xml.sax.Attributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Alexander Arakelyan on 15.12.15.
 */
public class NodeNamesAdapter {
	private Map<String, String> nodeNames = new HashMap<>();

	public NodeNamesAdapter(Class<?> propertyClassName) {
		loadProperties(propertyClassName);
	}

	private void loadProperties(Class<?> propertyClass) {
		Properties prop = new Properties();
		try (InputStream in = propertyClass.getResourceAsStream(propertyClass.getSimpleName() + ".properties")) {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Enumeration<?> propertyNames = prop.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String name = (String) propertyNames.nextElement();
			String value = prop.getProperty(name);
			nodeNames.put(name, value);
		}
	}


	public boolean byNode(String qName, String name) {
		return qName.equalsIgnoreCase(nodeNames.get(name));
	}

	public boolean byNodeEndsWith(String qName, String name) {
		return qName.endsWith(nodeNames.get(name));
	}

	public String byNodeAttr(Attributes attributes, String name) {
		return attributes.getValue(nodeNames.get(name));
	}
}
