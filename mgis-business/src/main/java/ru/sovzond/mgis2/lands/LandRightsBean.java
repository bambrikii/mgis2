package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.lands.rights.LandRights;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 */
@Service
public class LandRightsBean extends CRUDBeanBase<LandRights> {

	@Autowired
	private LandRightsDao dao;

	@Override
	protected IPageableDAOBase<LandRights> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandRights> getIIdentifiableDao() {
		return dao;
	}
}
