package ru.sovzond.mgis2.isogd.classifiers.documents;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.impl.PageableBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableCRUDDaoBase;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Repository
public class DocumentSubObjectDao extends PageableCRUDDaoBase<DocumentSubObject> {

	public static final String DOCUMENT_OBJECT = "documentObject";

	private class DocumentSubObjectBaseBuilder extends PageableBase<DocumentSubObject> {
		private DocumentObject documentObject;

		public DocumentSubObjectBaseBuilder(DocumentObject documentObject, int first, int max) {
			super(first, max);
			this.documentObject = documentObject;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(Restrictions.eq(DOCUMENT_OBJECT, documentObject));
		}
	}

	public PageableBase<DocumentSubObject> createFilter(DocumentObject documentObject, int first, int max) {
		return new DocumentSubObjectBaseBuilder(documentObject, first, max);
	}
}
