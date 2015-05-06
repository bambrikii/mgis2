package ru.sovzond.mgis2.authentication.dataaccess;

import ru.sovzond.mgis2.authentication.model.User;
import ru.sovzond.mgis2.dataaccess.base.IDAOBase;

public interface IUserDAO extends IDAOBase<User> {

	User findByName(String name);
}