package ru.sovzond.mgis2.isogd.classifiers.documents;

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
        public DocumentObjectFilterBuilder(DocumentClass documentClass) {
            addCriterion(Restrictions.eq("documentClass", documentClass));
        }
    }

    public PageableFilter<DocumentObject> createFilter(DocumentClass documentClass) {
        return new DocumentObjectFilterBuilder(documentClass);
    }
}
