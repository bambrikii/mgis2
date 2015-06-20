package ru.sovzond.mgis2.authentication.dataaccess;

import ru.sovzond.mgis2.authentication.model.User;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

public interface IUserDAO extends IPageableDAOBase<User>, IIdentifiableDao<User> {

	User findByName(String name);
}
