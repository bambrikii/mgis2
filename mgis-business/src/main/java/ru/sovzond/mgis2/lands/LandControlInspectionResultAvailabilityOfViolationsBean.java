package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.lands.control.LandControlAvailabilityOfViolations;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class LandControlInspectionResultAvailabilityOfViolationsBean extends CRUDBeanBase<LandControlAvailabilityOfViolations> {

	@Autowired
	private LandControlInspectionResultAvailabilityOfViolationsDao dao;

	@Override
	protected IPageableDAOBase<LandControlAvailabilityOfViolations> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlAvailabilityOfViolations> getIIdentifiableDao() {
		return dao;
	}
}
