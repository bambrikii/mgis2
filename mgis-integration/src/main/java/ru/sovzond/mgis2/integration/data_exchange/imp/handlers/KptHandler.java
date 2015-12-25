package ru.sovzond.mgis2.integration.data_exchange.imp.handlers;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.BuildingDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.IncompleteDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.handlers.kpt.KptCategoryPropertyExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.ILandResolver;

/**
 * Created by Alexander Arakelyan on 01/12/15.
 */
public class KptHandler extends RusRegisterHandlerBase {

	public KptHandler(
			ILandResolver<LandDTO> landResolver,
			ILandResolver<BuildingDTO> buildingResolver,
			ILandResolver<IncompleteDTO> incompleteConstructResolver
	) {
		super(
				landResolver,
				buildingResolver,
				incompleteConstructResolver,
				KptHandler.class,
				new KptCategoryPropertyExtractor()
		);
	}

}
