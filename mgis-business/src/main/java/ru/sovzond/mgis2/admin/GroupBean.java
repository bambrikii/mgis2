package ru.sovzond.mgis2.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.authentication.dataaccess.IGroupDAO;
import ru.sovzond.mgis2.authentication.model.Group;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by asd on 20/06/15.
 */
@Service
public class GroupBean extends CRUDBeanBase<Group> {

	@Autowired
	private IGroupDAO groupDAO;

	@Override
	protected IPageableDAOBase<Group> getPageableDao() {
		return groupDAO;
	}

	@Override
	protected IIdentifiableDao getIIdentifiableDao() {
		return groupDAO;
	}
}
