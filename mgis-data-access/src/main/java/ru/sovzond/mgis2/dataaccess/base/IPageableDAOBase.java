package ru.sovzond.mgis2.dataaccess.base;

import ru.sovzond.mgis2.dataaccess.base.impl.PageableBase;

import java.util.List;

public interface IPageableDAOBase<T> extends IDAOBase<T> {
    List<T> list(PageableBase<T> pageable);

    Number count();
}
