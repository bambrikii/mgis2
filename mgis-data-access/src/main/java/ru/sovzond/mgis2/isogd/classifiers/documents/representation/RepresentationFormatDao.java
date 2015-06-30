package ru.sovzond.mgis2.isogd.classifiers.documents.representation;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableCRUDDaoBase;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Repository
public class RepresentationFormatDao extends PageableCRUDDaoBase<RepresentationFormat> {
    public List<RepresentationFormat> findByName(String contentType) {
        return getSession().createCriteria(persistentClass)  //
                .add(Restrictions.eq("code", contentType)) //
                .list();
    }
}
