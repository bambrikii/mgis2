package ru.sovzond.mgis2.integration.data_exchange.imp.builders.base;

import java.util.function.Predicate;

/**
 * Created by Alexander Arakelyan on 24.12.15.
 */
public abstract class NodeBuilder<T> {
	private boolean condition;
	protected Object content;
	protected final NodeBuilderEndEvent<T> endEvent;
	private final NodeBuilder parent;
	private final Predicate<String> evaluator;

	public NodeBuilder(NodeBuilder parent, Predicate<String> evaluator) {
		this.parent = parent;
		this.endEvent = null;
		this.evaluator = evaluator;
	}

	public NodeBuilder(NodeBuilder parent, Predicate<String> evaluator, NodeBuilderEndEvent<T> endEvent) {
		this.parent = parent;
		this.endEvent = endEvent;
		this.evaluator = evaluator;
	}

	public boolean start(String qName, AttributeValueExtractor attributeValueExtractor) {
		if (parent == null) {
			throw new IllegalArgumentException("Parent required for condition checking.");
		}
		if (parent.isValid() && evaluator.test(qName)) {
			setValid();
			extractAttributes(attributeValueExtractor);
		}
		return isValid();
	}

	public void extractAttributes(AttributeValueExtractor attributeValueExtractor) {

	}

	public boolean content(Object content) {
		if (isValid()) {
			this.content = content;
		}
		return isValid();
	}

	public boolean end(String qName) {
		if (isValid() && evaluator.test(qName)) {
			content = null;
			setInvalid();
			if (endEvent != null) {
				endEvent.end(this);
			}
		}
		return isValid();
	}

	public abstract T build();

	public void reset() {
		content = null;
	}

	public boolean isValid() {
		return condition;
	}

	public void setValid() {
		condition = true;
	}

	public void setInvalid() {
		condition = false;
	}

}
