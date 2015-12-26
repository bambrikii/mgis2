package ru.sovzond.mgis2.integration.data_exchange.imp.builders.base;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
@FunctionalInterface
public interface NodeBuilderEndEvent<T> {
	void end(NodeBuilder<T> nodeBuilder);
}
