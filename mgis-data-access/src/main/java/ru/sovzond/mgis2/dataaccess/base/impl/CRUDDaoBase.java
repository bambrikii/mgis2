package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
public class CRUDDaoBase<T> extends DAOBase<T> implements IPageableDAOBase<T>, IIdentifiableDao<T> {

	public static final String ID = "id";
	public static final String NO_IDENTIFIERS_PROVIDED = "NO_IDENTIFIERS_PROVIDED";

	@Override
	public T findById(Long id) {
		return (T) createCriteria().add(Restrictions.eq(ID, id)).uniqueResult();
	}

	@Override
	public List<T> findByIds(List<Long> ids) {
		if (ids.size() == 0) {
			throw new IllegalArgumentException(NO_IDENTIFIERS_PROVIDED);
		}
		return createCriteria().add(Restrictions.in(ID, ids)).list();
	}

	protected Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public CRUDDaoBase() {
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public List<T> list(int firstResult, int maxResults) {
		return list(null);
	}

	@SuppressWarnings("unchecked")
	public List<T> list(PageableBase<T> filter) {
		Criteria criteria = createCriteria();
		if (filter != null) {
			filter.apply(criteria);
		}
		return criteria.list();
	}


	protected Criteria filter(Criterion criterion) {
		return createCriteria().add(criterion);
	}

	public Number count() {
		return count(null);
	}

	public Number count(PageableBase<T> filter) {
		Criteria criteria = createCriteria();
		if (filter != null) {
			filter.applyFilter(criteria);
		}
		return (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	protected Criteria createCriteria() {
		return getSession().createCriteria(persistentClass);
	}
}
