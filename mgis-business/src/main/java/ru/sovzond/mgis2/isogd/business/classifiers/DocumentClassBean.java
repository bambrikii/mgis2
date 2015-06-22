package ru.sovzond.mgis2.isogd.business.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClassDao;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class DocumentClassBean extends CRUDBeanBase<DocumentClass> {

    @Autowired
    private DocumentClassDao dao;

    @Override
    protected IPageableDAOBase<DocumentClass> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<DocumentClass> getIIdentifiableDao() {
        return dao;
    }
}
