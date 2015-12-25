package ru.sovzond.mgis2.integration.data_exchange.imp.builders.base;

import java.util.function.Predicate;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public abstract class HeirarchialNodeBuilder<T> extends NodeBuilder<T> {

	public HeirarchialNodeBuilder(NodeBuilder parent, Predicate<String> evaluator) {
		super(parent, evaluator);
	}

	public HeirarchialNodeBuilder(NodeBuilder parent, Predicate<String> evaluator, NodeBuilderEndEvent<T> endEvent) {
		super(parent, evaluator, endEvent);
	}

	@Override
	public boolean start(String qName, AttributeValueExtractor attributeValueExtractor) {
		boolean result = super.start(qName, attributeValueExtractor);
		if (!result) {
			return startCascade(qName, attributeValueExtractor);
		}
		return result;
	}

	@Override
	public boolean content(Object content) {
		if (isValid()) {
			contentCascade(content);
		}
		return isValid();
	}

	@Override
	public boolean end(String qName) {
		boolean result = super.end(qName);
		if (!result) {
			return endCascade(qName);
		}
		return result;
	}

	protected abstract boolean startCascade(String qName, AttributeValueExtractor attributeValueExtractor);

	protected abstract boolean endCascade(String qName);

	protected abstract boolean contentCascade(Object content);
}
