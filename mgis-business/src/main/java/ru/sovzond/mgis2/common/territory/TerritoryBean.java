package ru.sovzond.mgis2.common.territory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.common.classifiers.TerritoryDao;
import ru.sovzond.mgis2.common.classifiers.oktmo.Territory;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class TerritoryBean extends CRUDBeanBase<Territory> {

    @Autowired
    private TerritoryDao dao;

    @Override
    protected IPageableDAOBase<Territory> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<Territory> getIIdentifiableDao() {
        return dao;
    }
}
