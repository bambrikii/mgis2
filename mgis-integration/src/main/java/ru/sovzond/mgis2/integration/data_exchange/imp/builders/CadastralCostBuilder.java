package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.AttributeValueExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilder;

import java.util.function.Predicate;

import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.CADASTRAL_COST_UNIT_ATTR;
import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.CADASTRAL_COST_VALUE_ATTR;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public class CadastralCostBuilder extends NodeBuilder<Number[]> {
	private Double value;
	private Integer unit;

	public CadastralCostBuilder(ConstructBuilder constructBuilder, Predicate<String> cadastralCostPredicate) {
		super(constructBuilder, cadastralCostPredicate);
	}

	@Override
	public void extractAttributes(AttributeValueExtractor attributeValueExtractor) {
		value = Double.parseDouble(attributeValueExtractor.attribute(CADASTRAL_COST_VALUE_ATTR));
		unit = Integer.parseInt(attributeValueExtractor.attribute(CADASTRAL_COST_UNIT_ATTR));
	}

	@Override
	public Number[] buildImpl() {
		return new Number[]{value, unit};
	}

	@Override
	public void resetImpl() {
		super.resetImpl();
		value = null;
		unit = null;
	}

}
