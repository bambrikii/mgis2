package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 */
@Service
public class LandRightKindBean extends CRUDBeanBase<LandRightKind> {

	@Autowired
	private LandRightKindDao dao;

	@Override
	protected IPageableDAOBase<LandRightKind> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandRightKind> getIIdentifiableDao() {
		return dao;
	}

	public LandRightKind find(String classificationCode) {
		return dao.findByClassificationCode(classificationCode);
	}
}
