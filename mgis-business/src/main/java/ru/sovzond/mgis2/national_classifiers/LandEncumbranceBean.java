package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.national_classifiers.LandEncumbrance;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class LandEncumbranceBean extends CRUDBeanBase<LandEncumbrance> {

	@Autowired
	private LandEncumbranceDao dao;

	@Override
	protected IPageableDAOBase<LandEncumbrance> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandEncumbrance> getIIdentifiableDao() {
		return dao;
	}
}
