package ru.sovzond.mgis2.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.authentication.dataaccess.IUserDAO;
import ru.sovzond.mgis2.authentication.model.User;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by asd on 20/06/15.
 */
@Service
public class UserBean extends CRUDBeanBase<User> {

	@Autowired
	private IUserDAO userDAO;

	@Override
	protected IPageableDAOBase getPageableDao() {
		return userDAO;
	}

	@Override
	protected IIdentifiableDao getIIdentifiableDao() {
		return userDAO;
	}

}
