package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sovzond.mgis2.dataaccess.base.IDAOBase;

public abstract class DAOBase<T> implements IDAOBase<T> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().persist(entity);
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

}
