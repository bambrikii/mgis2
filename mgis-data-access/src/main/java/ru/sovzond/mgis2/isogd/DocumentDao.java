package ru.sovzond.mgis2.isogd;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.PageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableFilter;

@Repository
public class DocumentDao extends PageableDAOBase<Document> {
	public Document findById(Long id) {
		return (Document) filter(Restrictions.eq("id", id)).uniqueResult();
	}

	public DocumentFilterBuilder createFilter(Book book) {
		return new DocumentFilterBuilder(book);
	}

	class DocumentFilterBuilder extends PageableFilter<Document> {
		private DocumentFilterBuilder(Book book) {
			addCriterion(Restrictions.eq("book", book));
		}
	}
}
