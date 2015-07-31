package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.lands.control.LandControlPresenceOfViolations;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class LandControlPresenceOfViolationsBean extends CRUDBeanBase<LandControlPresenceOfViolations> {

	@Autowired
	private LandControlPresenceOfViolationsDao dao;

	@Override
	protected IPageableDAOBase<LandControlPresenceOfViolations> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlPresenceOfViolations> getIIdentifiableDao() {
		return dao;
	}
}
