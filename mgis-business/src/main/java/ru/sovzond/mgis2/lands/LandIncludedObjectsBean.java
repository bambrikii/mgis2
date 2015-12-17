package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.lands.includes.LandIncludedObjects;

/**
 * Created by Alexander Arakelyan on 16.12.15.
 */
@Service
public class LandIncludedObjectsBean extends CRUDBeanBase<LandIncludedObjects> {

	@Autowired
	private LandIncludedObjectsDao dao;

	@Override
	protected IPageableDAOBase<LandIncludedObjects> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandIncludedObjects> getIIdentifiableDao() {
		return dao;
	}
}
