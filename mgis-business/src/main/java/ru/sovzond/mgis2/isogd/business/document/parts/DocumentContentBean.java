package ru.sovzond.mgis2.isogd.business.document.parts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.isogd.document.DocumentContent;
import ru.sovzond.mgis2.isogd.document.DocumentContentDao;

/**
 * Created by Alexander Arakelyan on 30.06.15.
 */
@Service
public class DocumentContentBean extends CRUDBeanBase<DocumentContent> {

    @Autowired
    private DocumentContentDao dao;

    @Override
    protected IPageableDAOBase<DocumentContent> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<DocumentContent> getIIdentifiableDao() {
        return dao;
    }
}
