package ru.sovzond.mgis2.isogd.business.classifiers.representation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.isogd.classifiers.documents.representation.RepresentationFormat;
import ru.sovzond.mgis2.isogd.classifiers.documents.representation.RepresentationFormatDao;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class RepresentationFormatBean extends CRUDBeanBase<RepresentationFormat> {

    @Autowired
    private RepresentationFormatDao dao;

    @Override
    protected IPageableDAOBase<RepresentationFormat> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<RepresentationFormat> getIIdentifiableDao() {
        return dao;
    }

    public List<RepresentationFormat> find(String contentType) {
        return dao.findByName(contentType);
    }
}
