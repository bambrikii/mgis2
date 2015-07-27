package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.lands.LandAllowedUsageByDocument;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Service
public class LandAllowedUsageByDocumentBean extends CRUDBeanBase<LandAllowedUsageByDocument> {

	@Autowired
	private LandAllowedUsageByDocumentDao dao;

	@Override
	protected IPageableDAOBase<LandAllowedUsageByDocument> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandAllowedUsageByDocument> getIIdentifiableDao() {
		return dao;
	}
}
