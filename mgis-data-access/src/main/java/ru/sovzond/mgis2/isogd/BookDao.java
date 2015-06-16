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

	public BookFilterBuilder createFilter(Volume volume) {
		return new BookFilterBuilder(volume);
	}

	class BookFilterBuilder extends PageableFilter<Book> {
		private BookFilterBuilder(Volume volume) {
			addCriterion(Restrictions.eq("volume", volume));
		}
	}
}
