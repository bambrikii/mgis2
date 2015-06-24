package ru.sovzond.mgis2.isogd.document;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.impl.PageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableFilter;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.document.Document;

@Repository
public class DocumentDao extends PageableDAOBase<Document> {
	public Document findById(Long id) {
		return (Document) filter(Restrictions.eq("id", id)).uniqueResult();
	}

	public DocumentFilterBuilder createFilter(Volume volume) {
		return new DocumentFilterBuilder(volume);
	}

	class DocumentFilterBuilder extends PageableFilter<Document> {
		private DocumentFilterBuilder(Volume volume) {
			addCriterion(Restrictions.eq("volume", volume));
		}
	}
}