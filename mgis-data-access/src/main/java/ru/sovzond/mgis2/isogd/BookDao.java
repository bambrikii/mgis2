package ru.sovzond.mgis2.isogd;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.PageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableFilter;

@Repository
public class BookDao extends PageableDAOBase<Book> {
	public Book findById(Long id) {
		return (Book) filter(Restrictions.eq("id", id)).uniqueResult();
	}

	public BookFilterBuilder createFilter(Section section) {
		return new BookFilterBuilder(section);
	}

	class BookFilterBuilder extends PageableFilter<Book> {
		private BookFilterBuilder(Section section) {
			addCriterion(Restrictions.eq("section", section));
		}
	}
}
