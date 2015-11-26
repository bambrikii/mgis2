package ru.sovzond.mgis2.integration.data_exchange.imp;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;

/**
 * Created by Alexander Arakelyan on 26.11.15.
 */
public interface ILandResolver {

	/**
	 * @param land Resolve Coordinate Sytem
	 */
	void resolve(LandDTO land);

	/**
	 * @param coordinateSystemDTO Update Coordinate System for all the lands found and registered by resolve(...) method
	 */
	void updateCoordinateSystem(CoordinateSystemDTO coordinateSystemDTO);
}
