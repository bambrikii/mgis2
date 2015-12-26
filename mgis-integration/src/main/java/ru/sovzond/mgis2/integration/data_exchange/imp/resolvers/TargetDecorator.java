package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.geo.SpatialGroup;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */
public interface TargetDecorator<T> {
	TargetDecorator wrap(T land);

	Long getId();

	void setSpatialData(SpatialGroup spatialGroup);
}
