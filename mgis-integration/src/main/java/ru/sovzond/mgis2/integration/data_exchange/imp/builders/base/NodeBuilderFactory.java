package ru.sovzond.mgis2.integration.data_exchange.imp.builders.base;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public class NodeBuilderFactory {
	private NodeBuilderFactory() {
	}

	public static <T> NodeBuilder<T> createTrue() {
		return new NodeBuilder<T>(null, null) {

			@Override
			public boolean start(String qName, AttributeValueExtractor attributeValueExtractor) {
				return true;
			}

			@Override
			public boolean end(String qName) {
				return true;
			}

			@Override
			public boolean isValid() {
				return true;
			}

			@Override
			public T build() {
				throw new NotImplementedException();
			}
		};
	}
}
