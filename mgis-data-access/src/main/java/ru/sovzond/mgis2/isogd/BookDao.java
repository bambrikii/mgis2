package ru.sovzond.mgis2.isogd;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.impl.PageableBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableDAOBase;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;

@Repository
public class BookDao extends PageableDAOBase<Book> {
	public Book findById(Long id) {
		return (Book) filter(Restrictions.eq("id", id)).uniqueResult();
	}

	public BookBaseBuilder createFilter(Section section, int first, int max) {
		return new BookBaseBuilder(section, first, max);
	}

	public List<DocumentObject> listAvailableDocumentObjects(Section section) {
		return getSession()
				.createQuery("SELECT docObj FROM Section s JOIN s.documentClass docCls JOIN docCls.documentObjects docObj WHERE s = :sect ") //
				.setParameter("sect", section) //
				.list();
	}

	class BookBaseBuilder extends PageableBase<Book> {
		private Section section;

		private BookBaseBuilder(Section section, int first, int max) {
			super(first, max);
			this.section = section;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(Restrictions.eq("section", section));
		}
	}
}
