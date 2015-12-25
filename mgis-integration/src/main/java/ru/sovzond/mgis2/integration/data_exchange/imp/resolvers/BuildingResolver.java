package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.BuildingDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.BuildingResolverBean;

/**
 * Created by Alexander Arakelyan on 25/12/15.
 */
public class BuildingResolver implements ILandResolver<BuildingDTO> {
	public BuildingResolver(BuildingResolverBean buildingResolverBean) {

	}

	@Override
	public void resolve(BuildingDTO obj) {
		// TODO:
		System.out.println(obj.toString());
	}

	@Override
	public void updateCoordinateSystem(CoordinateSystemDTO coordinateSystemDTO) {
		// TODO:
	}
}
