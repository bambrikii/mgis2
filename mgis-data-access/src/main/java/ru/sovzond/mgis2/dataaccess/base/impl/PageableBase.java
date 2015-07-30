package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

public abstract class PageableBase<T> {

	public static final String ORDER_BY_COLUMN_SPLIT = "\\s+";
	public static final String DESC_ORDER_MARK = "desc";
	public static final int DEFAULT_LIMIT = -1;

	private String[] orderBy;
	private int first;
	private int max;

	protected PageableBase() {
		orderBy = null;
		first = DEFAULT_LIMIT;
		max = DEFAULT_LIMIT;
	}

	protected PageableBase(int first, int max) {
		orderBy = null;
		this.first = first;
		this.max = max;
	}

	protected PageableBase(String orderBy, int first, int max) {
		if (orderBy != null && !"".equals(orderBy.trim())) {
			this.orderBy = orderBy.split(",");
		}
		this.first = first;
		this.max = max;
	}

	public PageableBase<T> orderBy(String[] orderBy) {
		this.orderBy = orderBy;
		return this;
	}

	public PageableBase<T> first(int first) {
		this.first = first;
		return this;
	}

	public PageableBase<T> max(int max) {
		this.max = max;
		return this;
	}

	protected void apply(Criteria criteria) {
		applyFilter(criteria);
		applyOrder(criteria);
		applyLimit(criteria);
	}

	protected abstract void applyFilter(Criteria criteria);

	private void applyOrder(Criteria criteria) {
		if (orderBy != null && orderBy.length > 0) {
			for (String orderByString : orderBy) {
				String[] orderByArr = orderByString.split(ORDER_BY_COLUMN_SPLIT);
				criteria.addOrder(((orderByArr.length > 1) && orderByArr[1].toLowerCase().indexOf(DESC_ORDER_MARK) > -1) ? Order.desc(orderByArr[0])
						: Order.asc(orderByArr[0]));
			}
		}
	}

	private void applyLimit(Criteria criteria) {
		if (first != DEFAULT_LIMIT) {
			criteria.setFirstResult(first);
		}
		if (max != DEFAULT_LIMIT) {
			criteria.setMaxResults(max);
		}
	}

	public int getMax() {
		return max;
	}

	public int getFirst() {
		return first;
	}
}
