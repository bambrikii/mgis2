package ru.sovzond.mgis2.isogd.business.classifiers.representation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.isogd.classifiers.documents.representation.RepresentationForm;
import ru.sovzond.mgis2.isogd.classifiers.documents.representation.RepresentationFormDao;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class RepresentationFormBean extends CRUDBeanBase<RepresentationForm> {

    @Autowired
    private RepresentationFormDao dao;

    @Override
    protected IPageableDAOBase<RepresentationForm> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<RepresentationForm> getIIdentifiableDao() {
        return dao;
    }
}
