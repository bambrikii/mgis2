package ru.sovzond.mgis2.integration.data_exchange.imp.builders.base;

import java.util.function.Predicate;

/**
 * Created by Alexander Arakelyan on 24.12.15.
 */
public class DoubleNodeBuilder extends NodeBuilder<Double> {
	public DoubleNodeBuilder(NodeBuilder parent, Predicate<String> evaluator) {
		super(parent, evaluator);
	}

	@Override
	protected Double buildImpl() {
		return Double.parseDouble(content);
	}
}
