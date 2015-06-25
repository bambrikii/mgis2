package ru.sovzond.mgis2.isogd.classifiers.documents;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableCRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableFilter;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Repository
public class DocumentSubObjectDao extends PageableCRUDDaoBase<DocumentSubObject> {

    private class DocumentSubObjectFilterBuilder extends PageableFilter<DocumentSubObject> {
        private DocumentObject documentObject;

        public DocumentSubObjectFilterBuilder(DocumentObject documentObject) {
            this.documentObject = documentObject;
        }

        @Override
        protected void apply(Criteria criteria) {
            criteria.add(Restrictions.eq("documentObject", documentObject));
        }
    }

    public PageableFilter<DocumentSubObject> createFilter(DocumentObject documentObject) {
        return new DocumentSubObjectFilterBuilder(documentObject);
    }
}
