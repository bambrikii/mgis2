package ru.sovzond.mgis2.authentication.dataaccess.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.authentication.dataaccess.IUserDAO;
import ru.sovzond.mgis2.authentication.model.User;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;

import java.util.List;

@Repository
public class UserDAO extends CRUDDaoBase<User> implements IUserDAO {

	public static final String ID = "id";
	public static final String USERNAME = "username";

	@Override
	public User findById(Long id) {
		return (User) getSession().createCriteria(User.class).add(Restrictions.eq(ID, id)).uniqueResult();
	}

	@Override
	public List<User> findByIds(List<Long> ids) {
		return createCriteria().add(Restrictions.in(ID, ids)).list();
	}

	@Override
	public User findByName(String name) {
		return (User) getSession().createCriteria(User.class).add(Restrictions.eq(USERNAME, name)).uniqueResult();
	}
}
