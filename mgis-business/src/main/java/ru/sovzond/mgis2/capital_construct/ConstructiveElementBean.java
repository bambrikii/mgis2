package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.constructive_elements.ConstructiveElement;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
@Service
public class ConstructiveElementBean extends CRUDBeanBase<ConstructiveElement> {

	@Autowired
	private ConstructiveElementDao dao;

	@Override
	protected IPageableDAOBase<ConstructiveElement> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstructiveElement> getIIdentifiableDao() {
		return dao;
	}
}
