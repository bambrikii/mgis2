package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */
interface TargetDecorator<T> {
	TargetDecorator wrap(T land);

	Long getId();
}
