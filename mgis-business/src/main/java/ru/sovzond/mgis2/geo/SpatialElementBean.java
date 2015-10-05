package ru.sovzond.mgis2.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 05.10.15.
 */
@Service
public class SpatialElementBean extends CRUDBeanBase<SpatialElement> {

	@Autowired
	private SpatialElementDao dao;

	@Override
	protected IPageableDAOBase<SpatialElement> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<SpatialElement> getIIdentifiableDao() {
		return dao;
	}
}
