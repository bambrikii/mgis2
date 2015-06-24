package ru.sovzond.mgis2.business;

import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

import java.util.List;

/**
 * Created by asd on 20/06/15.
 */
public abstract class CRUDBeanBase<T> {

	protected abstract IPageableDAOBase<T> getPageableDao();

	protected abstract IIdentifiableDao<T> getIIdentifiableDao();

	public PageableContainer<T> list(int first, int max) {
		IPageableDAOBase<T> pageableDao = getPageableDao();
		return new PageableContainer<>(pageableDao.list(first, max), pageableDao.count());
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
