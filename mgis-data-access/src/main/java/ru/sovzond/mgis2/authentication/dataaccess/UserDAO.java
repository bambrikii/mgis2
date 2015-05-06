package ru.sovzond.mgis2.authentication.dataaccess;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.authentication.model.User;
import ru.sovzond.mgis2.dataaccess.base.DAOBase;

@Repository
public class UserDAO extends DAOBase<User> implements IUserDAO {

	public static final String USERNAME_EMPTY__ERROR = "USERNAME_EMPTY";
	public static final String USERNOTFOUND__ERROR = "USER_NOT_FOUND";

	@Override
	public User findByName(String name) {
		User result = (User) getSession().createCriteria(User.class).add(Restrictions.eq("username", name)).uniqueResult();
		return result;
	}

}
