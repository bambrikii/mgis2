package ru.sovzond.mgis2.integration.data_exchange.imp.handlers.region_cadastr;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.impl.PropertyExtractor;

import java.util.function.Supplier;

/**
 * Created by Alexander Arakelyan on 15.12.15.
 */
public class Region_CadastrCategoryPropertyExtractor implements PropertyExtractor<LandDTO, String> {
	@Override
	public void extractFromAttribute(LandDTO landDTO, Supplier<String> valueExtractor) {
		landDTO.setCategory(valueExtractor.get());
	}

	@Override
	public void extractFromChars(LandDTO landDTO, Supplier<String> valueExtractor) {

	}
}
