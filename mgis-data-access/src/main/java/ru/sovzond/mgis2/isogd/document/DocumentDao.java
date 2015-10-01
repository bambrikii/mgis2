package ru.sovzond.mgis2.isogd.document;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;

import java.util.Date;
import java.util.List;

@Repository
public class DocumentDao extends CRUDDaoBase<Document> {
	public Document findById(Long id) {
		return (Document) filter(Restrictions.eq("id", id)).uniqueResult();
	}

	public List<DocumentSubObject> listAvailableDocumentSubObjects(Volume volume) {
		// return volume.getBook().getDocumentObject().getDocumentSubObjects();
		return getSession() //
				.createQuery(
						"SELECT docSubObj FROM Volume vol JOIN vol.book book JOIN book.documentObject docObj JOIN docObj.documentSubObjects docSubObj WHERE vol = :vol ") //
				.setParameter("vol", volume) //
				.list();
	}

	public DocumentClass readDocumentClassByVolume(Volume volume) {
		return (DocumentClass) getSession() //
				.createQuery(
						"SELECT docClass FROM Volume vol JOIN vol.book book JOIN book.section sect JOIN sect.documentClass docClass WHERE vol = :vol") //
				.setParameter("vol", volume) //
				.uniqueResult();
	}

	class DocumentBaseBuilder extends PagerBuilderCriteria<Document> {
		private Volume volume;

		private DocumentBaseBuilder(Volume volume, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.volume = volume;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(Restrictions.eq("volume", volume));
		}
	}

	public PagerBuilderCriteria<Document> createFilter(Volume volume, String order, int first, int max) {
		return new DocumentBaseBuilder(volume, order, first, max);
	}

	public PagerBuilderCriteria<Document> createSearchDocumentFilter(Section section, String documentName, Date documentDate, String documentNumber, String orderBy, int first, int max) {
		return new SearchDocumentFilter(section, documentName, documentDate, documentNumber, orderBy, first, max);
	}

	private class SearchDocumentFilter extends PagerBuilderCriteria<Document> {
		private Section section;
		private String documentName;
		private Date documentDate;
		private String documentNumber;

		private SearchDocumentFilter(Section section, String documentName, Date documentDate, String documentNumber, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.section = section;
			this.documentName = documentName;
			this.documentDate = documentDate;
			this.documentNumber = documentNumber;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.createAlias("volume", "vol");
			criteria.createAlias("vol.book", "bk");
			criteria.createAlias("bk.section", "section");
			if (section != null) {
				criteria.add(Restrictions.eq("bk.section", section));
			}
			if (documentName != null && documentName.length() > 0) {
				criteria.add(Restrictions.like("name", "%" + documentName + "%"));
			}
			if (documentDate != null) {
				criteria.add(Restrictions.eq("docDate", documentDate));
			}
			if (documentNumber != null && documentNumber.length() > 0) {
				criteria.add(Restrictions.like("docNumber", "%" + documentNumber + "%"));
			}
		}
	}
}
