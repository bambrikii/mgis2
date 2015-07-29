package ru.sovzond.mgis2.national_classifiers;

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
public class TerritorialZoneBean extends CRUDBeanBase<TerritorialZone> {

	@Autowired
	private TerritorialZoneDao dao;

	@Override
	protected IPageableDAOBase<TerritorialZone> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<TerritorialZone> getIIdentifiableDao() {
		return dao;
	}
}
