package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZone;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Service
public class LandAllowedUsageByTerritorialZoneBean extends CRUDBeanBase<TerritorialZone> {

	@Autowired
	private LandAllowedUsageByTerritorialZoneDao dao;

	@Override
	protected IPageableDAOBase<TerritorialZone> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<TerritorialZone> getIIdentifiableDao() {
		return null;
	}
}
