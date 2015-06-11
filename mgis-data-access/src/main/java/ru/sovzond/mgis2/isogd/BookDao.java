package ru.sovzond.mgis2.isogd;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.PageableDAOBase;

@Repository
public class BookDao extends PageableDAOBase<Book> {
	public Book findById(Long id) {
		return (Book) filter(Restrictions.eq("id", id)).uniqueResult();
	}
}
