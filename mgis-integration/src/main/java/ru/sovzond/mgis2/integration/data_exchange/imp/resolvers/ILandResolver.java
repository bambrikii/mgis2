package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;

/**
 * Created by Alexander Arakelyan on 26.11.15.
 */
public interface ILandResolver<T> {

	/**
	 * @param obj Resolve Coordinate Sytem
	 */
	void resolve(T obj);

	/**
	 * @param coordinateSystemDTO Update Coordinate System for all the lands found and registered by resolve(...) method
	 */
	void updateCoordinateSystem(CoordinateSystemDTO coordinateSystemDTO);
}
