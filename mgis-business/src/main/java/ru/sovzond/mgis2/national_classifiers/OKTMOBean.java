package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Service
public class OKTMOBean extends CRUDBeanBase<OKTMO> {

	@Autowired
	private OKTMODao dao;


	@Override
	protected IPageableDAOBase<OKTMO> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<OKTMO> getIIdentifiableDao() {
		return dao;
	}
}
