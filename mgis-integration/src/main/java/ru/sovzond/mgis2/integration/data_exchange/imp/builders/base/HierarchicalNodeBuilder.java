package ru.sovzond.mgis2.integration.data_exchange.imp.builders.base;

import java.util.function.Predicate;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public abstract class HierarchicalNodeBuilder<T> extends NodeBuilder<T> {

	public HierarchicalNodeBuilder(NodeBuilder parent, Predicate<String> evaluator) {
		super(parent, evaluator);
	}

	public HierarchicalNodeBuilder(NodeBuilder parent, Predicate<String> evaluator, NodeBuilderEndEvent<T> endEvent) {
		super(parent, evaluator, endEvent);
	}

	@Override
	public boolean start(String qName, AttributeValueExtractor attributeValueExtractor) {
		if (parent == null) {
			throw new IllegalArgumentException("Parent required for condition checking.");
		}
		if (parent.isActive()) {
			if (evaluator.test(qName)) {
				setActive();
				extractAttributes(attributeValueExtractor);
			} else {
				return startCascade(qName, attributeValueExtractor);
			}
		}
		return isActive();
	}

	@Override
	public boolean content(String content) {
		if (isActive()) {
			contentCascade(content);
		}
		return isActive();
	}

	@Override
	public boolean end(String qName) {
		if (isActive()) {
			if (evaluator.test(qName)) {
				setInactive();
				if (endEvent != null) {
					endEvent.end(this);
				}
			} else {
				return endCascade(qName);
			}
		}
		return isActive();
	}

	protected abstract NodeBuilder[] children();

	private final boolean startCascade(String qName, AttributeValueExtractor attributeValueExtractor) {
		NodeBuilder[] children = children();
		for (int i = 0; i < children.length; i++) {
			if (children[i].start(qName, attributeValueExtractor)) {
				return true;
			}
		}
		return false;
	}

	private final boolean endCascade(String qName) {
		NodeBuilder[] children = children();
		for (int i = 0; i < children.length; i++) {
			if (children[i].end(qName)) {
				return true;
			}
		}
		return false;
	}

	private final boolean contentCascade(String content) {
		NodeBuilder[] children = children();
		for (int i = 0; i < children.length; i++) {
			if (children[i].content(content)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void resetImpl() {
		NodeBuilder[] children = children();
		for (int i = 0; i < children.length; i++) {
			children[i].reset();
		}
	}

}
