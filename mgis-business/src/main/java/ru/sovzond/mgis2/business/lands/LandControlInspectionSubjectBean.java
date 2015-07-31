package ru.sovzond.mgis2.business.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.lands.LandControlInspectionSubjectDao;
import ru.sovzond.mgis2.registers.lands.control.LandControlInspectionSubject;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class LandControlInspectionSubjectBean extends CRUDBeanBase<LandControlInspectionSubject> {

	@Autowired
	private LandControlInspectionSubjectDao dao;

	@Override
	protected IPageableDAOBase<LandControlInspectionSubject> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlInspectionSubject> getIIdentifiableDao() {
		return dao;
	}
}
