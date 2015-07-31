package ru.sovzond.mgis2.business.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.lands.LandControlInspectionKindDao;
import ru.sovzond.mgis2.registers.lands.control.LandControlInspectionKind;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class LandControlInspectionKindBean extends CRUDBeanBase<LandControlInspectionKind> {

	@Autowired
	private LandControlInspectionKindDao dao;

	@Override
	protected IPageableDAOBase<LandControlInspectionKind> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlInspectionKind> getIIdentifiableDao() {
		return dao;
	}
}
