package ru.sovzond.mgis2.integration.data_exchange.imp.handlers;

import ru.sovzond.mgis2.integration.data_exchange.imp.ILandResolver;
import ru.sovzond.mgis2.integration.data_exchange.imp.handlers.kpt.KptCategoryPropertyExtractor;

/**
 * Created by Alexander Arakelyan on 01/12/15.
 */
public class KptHandler extends RusRegisterHandlerBase {

	public KptHandler(ILandResolver landResolver) {
		super(landResolver, KptHandler.class, new KptCategoryPropertyExtractor());
	}

}
