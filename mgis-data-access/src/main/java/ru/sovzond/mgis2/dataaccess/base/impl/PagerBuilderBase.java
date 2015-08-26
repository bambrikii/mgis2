package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Alexander Arakelyan on 26.08.15.
 */
public abstract class PagerBuilderBase<T> {
	public static final String ORDER_BY_COLUMN_SPLIT = "\\s+";
	public static final String DESC_ORDER_MARK = "desc";
	public static final int DEFAULT_LIMIT = -1;
	protected String[] orderBy;
	protected int first;
	protected int max;

	protected final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public PagerBuilderBase() {
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected PagerBuilderBase(String orderBy, int first, int max) {
		this();
		setParameters(orderBy, first, max);
	}

	protected PagerBuilderBase(Class<T> cls, int first, int max) {
		persistentClass = cls;
		setParameters(null, first, max);
	}

	public PagerBuilderBase(Class<T> cls, String orderBy, int first, int max) {
		persistentClass = cls;
		setParameters(orderBy, first, max);
	}

	private void setParameters(String orderBy, int first, int max) {
		if (orderBy != null && !"".equals(orderBy.trim())) {
			this.orderBy = orderBy.split(",");
		}
		this.first = first;
		this.max = max;
	}


	public PagerBuilderBase<T> orderBy(String[] orderBy) {
		this.orderBy = orderBy;
		return this;
	}

	public PagerBuilderBase<T> first(int first) {
		this.first = first;
		return this;
	}

	public PagerBuilderBase<T> max(int max) {
		this.max = max;
		return this;
	}


	public int getMax() {
		return max;
	}

	public int getFirst() {
		return first;
	}

	protected abstract Pageable<T> apply(Session session);

//	public PageableContainer<T> pager(Session session) {
//		Pageable<T> pager = apply(session);
//		return new PageableContainer<>(pager.list(), pager.count(), first, max);
//	}
}
