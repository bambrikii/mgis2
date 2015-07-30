package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.national_classifiers.LandOwnershipForm;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 */
@Service
public class LandOwnershipFormBean extends CRUDBeanBase<LandOwnershipForm> {

	@Autowired
	private LandOwnershipFormDao dao;

	@Override
	protected IPageableDAOBase<LandOwnershipForm> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandOwnershipForm> getIIdentifiableDao() {
		return dao;
	}
}
