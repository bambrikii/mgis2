package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.AttributeValueExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilder;

import java.util.function.Predicate;

import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.TYPE_ALL_ATTR;
import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.VALUE_ATTR;

/**
 * Created by Alexander Arakelyan on 24.12.15.
 */
public class AddressLevel1Builder extends NodeBuilder<String[]> {

	private String[] values = new String[2];

	public AddressLevel1Builder(NodeBuilder parent, Predicate<String> evaluator) {
		super(parent, evaluator);
	}

	@Override
	public void extractAttributes(AttributeValueExtractor attributeValueExtractor) {
		values[0] = attributeValueExtractor.attribute(TYPE_ALL_ATTR);
		values[1] = attributeValueExtractor.attribute(VALUE_ATTR);
	}

	@Override
	public void resetImpl() {
		super.resetImpl();
		values[0] = null;
		values[1] = null;
	}

	@Override
	public String[] buildImpl() {
		return values;
	}
}
