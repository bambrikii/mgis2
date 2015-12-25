package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.AttributeValueExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilder;

import java.util.function.Predicate;

import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.NAME_ALL_ATTR;
import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.TYPE_ALL_ATTR;

/**
 * Created by Alexander Arakelyan on 24.12.15.
 */
public class AddressElementBuilder extends NodeBuilder<String[]> {

	private String name;
	private String type;

	public AddressElementBuilder(NodeBuilder parent, Predicate<String> evaluator) {
		super(parent, evaluator);
	}

	@Override
	public void extractAttributes(AttributeValueExtractor attributeValueExtractor) {
		name = (String) attributeValueExtractor.attribute(NAME_ALL_ATTR);
		type = (String) attributeValueExtractor.attribute(TYPE_ALL_ATTR);
	}

	@Override
	public void reset() {
		super.reset();
		name = null;
		type = null;
	}

	@Override
	public String[] build() {
		return new String[]{name, type};
	}
}
