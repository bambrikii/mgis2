package ru.sovzond.mgis2.dataaccess.base;

import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;

public interface IPageableDAOBase<T> extends IDAOBase<T> {
	Pageable<T> pager(PagerBuilderBase<T> pageable);
}
