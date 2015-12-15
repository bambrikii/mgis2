package ru.sovzond.mgis2.integration.data_exchange.imp.handlers.kpt;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.impl.PropertyExtractor;

import java.util.function.Supplier;

/**
 * Created by Alexander Arakelyan on 15.12.15.
 */
public class KptCategoryPropertyExtractor implements PropertyExtractor<LandDTO, String> {
	@Override
	public void extractFromAttribute(LandDTO landDTO, Supplier<String> valueExtractor) {

	}

	@Override
	public void extractFromChars(LandDTO landDTO, Supplier<String> valueExtractor) {
		landDTO.setCategory(valueExtractor.get());
	}
}
