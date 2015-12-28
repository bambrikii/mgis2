package ru.sovzond.mgis2.integration.data_exchange.imp.builders.base;

import java.util.function.Predicate;

/**
 * Created by Alexander Arakelyan on 24.12.15.
 */
public abstract class NodeBuilder<T> {
	private boolean active;
	protected String content;
	protected final NodeBuilderEndEvent<T> endEvent;
	protected final NodeBuilder parent;
	protected final Predicate<String> evaluator;
	private boolean visited;

	protected NodeBuilder(NodeBuilder parent, Predicate<String> evaluator) {
		this.parent = parent;
		this.endEvent = null;
		this.evaluator = evaluator;
	}

	protected NodeBuilder(NodeBuilder parent, Predicate<String> evaluator, NodeBuilderEndEvent<T> endEvent) {
		this.parent = parent;
		this.endEvent = endEvent;
		this.evaluator = evaluator;
	}

	public boolean start(String qName, AttributeValueExtractor attributeValueExtractor) {
		if (parent == null) {
			throw new IllegalArgumentException("Parent required for active checking.");
		}
		if (parent.isActive() && evaluator.test(qName)) {
			setActive();
			extractAttributes(attributeValueExtractor);
		}
		return isActive();
	}

	protected void extractAttributes(AttributeValueExtractor attributeValueExtractor) {

	}

	public boolean content(String content) {
		if (isActive()) {
			this.content = content;
		}
		return isActive();
	}

	public boolean end(String qName) {
		if (isActive() && evaluator.test(qName)) {
			setInactive();
			if (endEvent != null) {
				endEvent.end(this);
			}
		}
		return isActive();
	}

	public final T build() {
		if (isVisited()) {
			return buildImpl();
		}
		return null;
	}

	protected abstract T buildImpl();

	public final void reset() {
		content = null;
		active = false;
		visited = false;
		resetImpl();
	}

	protected void resetImpl() {
	}

	protected boolean isActive() {
		return active;
	}

	protected void setActive() {
		active = true;
		visited = true;
	}

	protected final void setInactive() {
		active = false;
	}

	protected final boolean isVisited() {
		return visited;
	}

}
