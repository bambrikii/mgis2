package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.util.List;

public abstract class PagerBuilderCriteria<T> extends PagerBuilderBase<T> {

	protected PagerBuilderCriteria() {
		super((String) null, DEFAULT_LIMIT, DEFAULT_LIMIT);
	}

	protected PagerBuilderCriteria(int first, int max) {
		super((String) null, first, max);
	}

	public PagerBuilderCriteria(Class<T> cls, String orderBy, int first, int max) {
		super(cls, orderBy, first, max);
	}

	public PagerBuilderCriteria(String orderBy, int first, int max) {
		super(orderBy, first, max);
	}

	public PagerBuilderCriteria(Class<T> cls, int first, int max) {
		super(cls, first, max);
	}

	protected Pageable apply(Session session) {
		Criteria criteria = session.createCriteria(persistentClass);
		applyFilter(criteria);
		applyOrder(criteria);
		applyLimit(criteria);
		return new Pageable() {
			@Override
			public List<T> list() {
				Criteria criteria = session.createCriteria(persistentClass);
				applyFilter(criteria);
				applyOrder(criteria);
				applyLimit(criteria);
				criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
				return criteria.list();
			}

			@Override
			public Number count() {
				Criteria criteria = session.createCriteria(persistentClass);
				applyFilter(criteria);
				return (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
		};
	}

	protected abstract void applyFilter(Criteria criteria);

	protected void applyOrder(Criteria criteria) {
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

}
