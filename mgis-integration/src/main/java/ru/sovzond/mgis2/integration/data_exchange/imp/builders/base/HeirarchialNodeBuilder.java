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
		if (parent == null) {
			throw new IllegalArgumentException("Parent required for condition checking.");
		}
		if (parent.isValid()) {
			if (evaluator.test(qName)) {
				setValid();
				extractAttributes(attributeValueExtractor);
			} else {
				return startCascade(qName, attributeValueExtractor);
			}
		}
		return isValid();
	}

	@Override
	public boolean content(String content) {
		if (isValid()) {
			contentCascade(content);
		}
		return isValid();
	}

	@Override
	public boolean end(String qName) {
		if (isValid()) {
			if (evaluator.test(qName)) {
				setInvalid();
				if (endEvent != null) {
					endEvent.end(this);
				}
			} else {
				return endCascade(qName);
			}
		}
		return isValid();
	}

	protected abstract boolean startCascade(String qName, AttributeValueExtractor attributeValueExtractor);

	protected abstract boolean endCascade(String qName);

	protected abstract boolean contentCascade(String content);
}
