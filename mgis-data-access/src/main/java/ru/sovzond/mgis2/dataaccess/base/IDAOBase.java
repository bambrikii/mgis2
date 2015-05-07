package ru.sovzond.mgis2.dataaccess.base;

public interface IDAOBase<T> {

	void persist(T entity);

	void delete(T entity);

}