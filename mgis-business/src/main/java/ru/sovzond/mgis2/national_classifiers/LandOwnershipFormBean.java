package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.national_classifiers.OKFS;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 */
@Service
public class LandOwnershipFormBean extends CRUDBeanBase<OKFS> {

	@Autowired
	private LandOwnershipFormDao dao;

	@Override
	protected IPageableDAOBase<OKFS> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<OKFS> getIIdentifiableDao() {
		return dao;
	}
}
