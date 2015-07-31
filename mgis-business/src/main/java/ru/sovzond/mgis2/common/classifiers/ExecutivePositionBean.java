package ru.sovzond.mgis2.common.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class ExecutivePositionBean extends CRUDBeanBase<ExecutivePerson> {

	@Autowired
	private ExecutiveDao dao;


	@Override
	protected IPageableDAOBase<ExecutivePerson> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ExecutivePerson> getIIdentifiableDao() {
		return dao;
	}
}
