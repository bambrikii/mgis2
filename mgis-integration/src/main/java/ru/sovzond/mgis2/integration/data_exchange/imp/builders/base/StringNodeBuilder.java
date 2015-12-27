package ru.sovzond.mgis2.integration.data_exchange.imp.builders.base;

import java.util.function.Predicate;

/**
 * Created by Alexander Arakelyan on 24.12.15.
 */
public class StringNodeBuilder extends NodeBuilder<String> {
	public StringNodeBuilder(NodeBuilder parent, Predicate<String> evaluator) {
		super(parent, evaluator);
	}

	@Override
	protected String buildImpl() {
		return content;
	}
}
