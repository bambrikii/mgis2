package ru.sovzond.mgis2.integration.data_exchange.imp.impl;

import java.util.function.Supplier;

/**
 * Created by Alexander Arakelyan on 15.12.15.
 */
public interface PropertyExtractor<T, V> {
	void extractFromAttribute(T object, Supplier<V> valueExtractor);

	void extractFromChars(T object, Supplier<V> valueExtractor);
}
