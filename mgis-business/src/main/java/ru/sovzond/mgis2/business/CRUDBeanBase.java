package ru.sovzond.mgis2.business;

import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerFactory;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 20/06/15.
 */
public abstract class CRUDBeanBase<T> {

	protected final Class<T> persistentClass;

	public CRUDBeanBase() {
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected abstract IPageableDAOBase<T> getPageableDao();

	protected abstract IIdentifiableDao<T> getIIdentifiableDao();

	public PageableContainer<T> list(int first, int max) {
		Pageable<T> pager = getPageableDao().pager(PagerFactory.createDefault(persistentClass, first, max));
		return new PageableContainer<>(pager.list(), pager.count(), first, max);
	}

	public PageableContainer<T> list(String orderBy, int first, int max) {
		IPageableDAOBase<T> pageableDao = getPageableDao();
		Pageable<T> pager = pageableDao.pager(PagerFactory.createDefault(persistentClass, orderBy, first, max));
		return new PageableContainer<>(pager.list(), pager.count(), first, max);
	}

	public T load(Long id) {
		return getIIdentifiableDao().findById(id);
	}


	public List<T> load(List<Long> ids) {
		return getIIdentifiableDao().findByIds(ids);
	}

	public T save(T entity) {
		getPageableDao().save(entity);
		return entity;
	}

	public void remove(T entity) {
		getPageableDao().delete(entity);
	}
}
