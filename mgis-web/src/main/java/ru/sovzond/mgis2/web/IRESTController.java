package ru.sovzond.mgis2.web;

import org.springframework.web.bind.annotation.RequestBody;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

/**
 * Created by asd on 20/06/15.
 */
public interface IRESTController<T> {
	PageableContainer<T> list(int first, int max);

	T save(Long id, @RequestBody T ent);

	T read(Long id);

	void delete(Long id);
}
