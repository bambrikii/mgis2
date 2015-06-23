package ru.sovzond.mgis2.isogd.business.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableFilter;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObjectDao;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class DocumentObjectBean extends CRUDBeanBase<DocumentObject> {
    @Autowired
    private DocumentObjectDao dao;

    @Override
    protected IPageableDAOBase<DocumentObject> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<DocumentObject> getIIdentifiableDao() {
        return dao;
    }

    public PageableContainer<DocumentObject> list(DocumentClass documentClass, int first, int max) {
        PageableFilter<DocumentObject> filter = dao.createFilter(documentClass);
        return new PageableContainer<>(dao.list(first, max, filter), dao.count(filter));
    }
}
