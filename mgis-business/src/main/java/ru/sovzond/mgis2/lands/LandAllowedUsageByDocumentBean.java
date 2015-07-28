package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.national_classifiers.LandAllowedUsage;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Service
public class LandAllowedUsageByDocumentBean extends CRUDBeanBase<LandAllowedUsage> {

	@Autowired
	private LandAllowedUsageByDocumentDao dao;

	@Override
	protected IPageableDAOBase<LandAllowedUsage> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandAllowedUsage> getIIdentifiableDao() {
		return dao;
	}
}
