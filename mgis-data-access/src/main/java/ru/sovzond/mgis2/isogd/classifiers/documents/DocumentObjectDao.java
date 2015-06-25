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
public class DocumentObjectDao extends PageableCRUDDaoBase<DocumentObject> {
    private class DocumentObjectFilterBuilder extends PageableFilter<DocumentObject> {
        private DocumentClass documentClass;

        public DocumentObjectFilterBuilder(DocumentClass documentClass) {
            this.documentClass = documentClass;
        }

        @Override
        protected void apply(Criteria criteria) {
            criteria.add(Restrictions.eq("documentClass", documentClass));
        }
    }

    public PageableFilter<DocumentObject> createFilter(DocumentClass documentClass) {
        return new DocumentObjectFilterBuilder(documentClass);
    }
}
