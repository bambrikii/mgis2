package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 03.08.15.
 */
@Service
public class LandAreaTypeBean extends CRUDBeanBase<LandAreaType> {

	@Autowired
	private LandAreaTypeDao dao;

	@Override
	protected IPageableDAOBase<LandAreaType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandAreaType> getIIdentifiableDao() {
		return dao;
	}
}
