package ru.sovzond.mgis2.dataaccess.base.impl;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 26.08.15.
 */

public interface Pageable<T> {
	List<T> list();

	Number count();
}
