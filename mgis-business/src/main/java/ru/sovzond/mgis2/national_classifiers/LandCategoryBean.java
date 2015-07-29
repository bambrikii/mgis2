package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.national_classifiers.LandCategory;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@Service
public class LandCategoryBean extends CRUDBeanBase<LandCategory> {

	@Autowired
	private LandCategoryDao dao;

	@Override
	protected IPageableDAOBase<LandCategory> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandCategory> getIIdentifiableDao() {
		return dao;
	}
}
