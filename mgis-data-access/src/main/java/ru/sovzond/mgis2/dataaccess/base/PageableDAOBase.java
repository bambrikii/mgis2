package ru.sovzond.mgis2.dataaccess.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

public abstract class PageableDAOBase<T> extends DAOBase<T> {
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public PageableDAOBase() {
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public List<T> list(int firstResult, int maxResults) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		if (firstResult > 0) {
			criteria.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			criteria.setMaxResults(maxResults);
		}
		return criteria.list();
	}

	public Number count() {
		Criteria criteria = getSession().createCriteria(persistentClass);
		return (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

}
