package ru.sovzond.mgis2.authentication.dataaccess;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.authentication.model.User;
import ru.sovzond.mgis2.dataaccess.base.PageableDAOBase;

@Repository
public class UserDAO extends PageableDAOBase<User> implements IUserDAO {

	@Override
	public User findByName(String name) {
		User result = (User) getSession().createCriteria(User.class).add(Restrictions.eq("username", name)).uniqueResult();
		return result;
	}

}
