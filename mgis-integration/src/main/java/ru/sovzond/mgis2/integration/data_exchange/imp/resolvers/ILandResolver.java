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

	void updateCoordinateSystem(CoordinateSystemDTO coordinateSystemDTO);
}
