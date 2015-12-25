package ru.sovzond.mgis2.integration.data_exchange.imp.builders.base;

import org.xml.sax.Attributes;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public interface NodeFilter {
	boolean test(String qName, Attributes attributes);

	boolean test(String qName);
}
