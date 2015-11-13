package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.constructive_elements.ConstructiveElementType;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 13/11/15.
 */
@Service
public class ConstructiveElementTypeBean extends CRUDBeanBase<ConstructiveElementType> {

	@Autowired
	private ConstructiveElementTypeDao dao;

	@Override
	protected IPageableDAOBase<ConstructiveElementType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstructiveElementType> getIIdentifiableDao() {
		return dao;
	}
}
