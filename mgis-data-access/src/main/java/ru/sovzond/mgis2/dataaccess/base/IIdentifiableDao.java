package ru.sovzond.mgis2.dataaccess.base;

import java.util.List;

/**
 * Created by asd on 20/06/15.
 */
public interface IIdentifiableDao<T> {
	T findById(Long id);

	List<T> findByIds(List<Long> ids);
}
