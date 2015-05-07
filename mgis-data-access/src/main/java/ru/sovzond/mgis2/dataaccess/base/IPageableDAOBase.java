package ru.sovzond.mgis2.dataaccess.base;

import java.util.List;

public interface IPageableDAOBase<T> extends IDAOBase<T> {
	List<T> list(int firstResult, int maxResults);

	Number count();
}
