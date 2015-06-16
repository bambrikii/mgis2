package ru.sovzond.mgis2.dataaccess.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;

public abstract class PageableDAOBase<T> extends DAOBase<T> {
	protected Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public PageableDAOBase() {
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public List<T> list(int firstResult, int maxResults) {
		return list(firstResult, maxResults, null);
	}

	@SuppressWarnings("unchecked")
	public List<T> list(int firstResult, int maxResults, PageableFilter<T> filter) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		applyFilter(filter, criteria);
		if (firstResult > 0) {
			criteria.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			criteria.setMaxResults(maxResults);
		}
		return criteria.list();
	}

	private void applyFilter(PageableFilter<T> filter, Criteria criteria) {
		if (filter != null) {
			for (Criterion criterion : filter.getFilterItems()) {
				criteria.add(criterion);
			}
		}
	}

	protected Criteria filter(Criterion criterion) {
		return getSession().createCriteria(persistentClass).add(criterion);
	}

	public Number count() {
		return count(null);
	}

	public Number count(PageableFilter<T> filter) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		applyFilter(filter, criteria);
		return (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
}
