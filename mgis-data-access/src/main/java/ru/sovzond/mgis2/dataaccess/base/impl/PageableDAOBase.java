package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class PageableDAOBase<T> extends DAOBase<T> implements IPageableDAOBase<T> {
    protected Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public PageableDAOBase() {
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
