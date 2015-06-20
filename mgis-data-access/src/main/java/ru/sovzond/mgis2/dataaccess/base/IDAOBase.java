package ru.sovzond.mgis2.dataaccess.base;

public interface IDAOBase<T> {

	void save(T entity);

	void delete(T entity);

}
