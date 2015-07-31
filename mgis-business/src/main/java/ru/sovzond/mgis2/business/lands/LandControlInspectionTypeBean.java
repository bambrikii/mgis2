package ru.sovzond.mgis2.business.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.lands.LandControlInspectionTypeDao;
import ru.sovzond.mgis2.registers.lands.control.LandControlInspectionType;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class LandControlInspectionTypeBean extends CRUDBeanBase<LandControlInspectionType> {

	@Autowired
	private LandControlInspectionTypeDao dao;

	@Override
	protected IPageableDAOBase<LandControlInspectionType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlInspectionType> getIIdentifiableDao() {
		return dao;
	}
}
