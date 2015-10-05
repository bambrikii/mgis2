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
public class SpatialGroupBean extends CRUDBeanBase<SpatialGroup> {
	@Autowired
	private SpatialGroupDao dao;

	@Override
	protected IPageableDAOBase<SpatialGroup> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<SpatialGroup> getIIdentifiableDao() {
		return dao;
	}
}
