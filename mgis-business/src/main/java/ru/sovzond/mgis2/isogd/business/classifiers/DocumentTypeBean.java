package ru.sovzond.mgis2.isogd.business.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentType;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentTypeDao;

/**
 * Created by asd on 22.06.15.
 */
@Service
public class DocumentTypeBean extends CRUDBeanBase<DocumentType> {

    @Autowired
    private DocumentTypeDao dao;

    @Override
    protected IPageableDAOBase<DocumentType> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<DocumentType> getIIdentifiableDao() {
        return dao;
    }
}
