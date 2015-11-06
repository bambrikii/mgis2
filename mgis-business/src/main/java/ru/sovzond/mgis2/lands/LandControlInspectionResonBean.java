package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.lands.control.LandControlInspectionReason;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class LandControlInspectionResonBean extends CRUDBeanBase<LandControlInspectionReason> {

	@Autowired
	private LandControlInspectionResonDao dao;

	@Override
	protected IPageableDAOBase<LandControlInspectionReason> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlInspectionReason> getIIdentifiableDao() {
		return dao;
	}
}
