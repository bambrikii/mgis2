package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.IncompleteDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.IncompleteConstructResolverBean;

/**
 * Created by Alexander Arakelyan on 25/12/15.
 */
public class IncompleteConstructResolver implements ILandResolver<IncompleteDTO> {
	public IncompleteConstructResolver(IncompleteConstructResolverBean incompleteConstructResolverBean) {

	}

	@Override
	public void resolve(IncompleteDTO obj) {
		// TODO:
		System.out.println(obj.toString());
	}

	@Override
	public void updateCoordinateSystem(CoordinateSystemDTO coordinateSystemDTO) {
		// TODO:
	}
}
