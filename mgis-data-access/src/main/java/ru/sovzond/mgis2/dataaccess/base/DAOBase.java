package ru.sovzond.mgis2.dataaccess.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DAOBase<T> implements IDAOBase<T> {

	@Autowired
	private SessionFactory sessionFactory;

	// public void setSessionFactory(SessionFactory sessionFactory) {
	// this.sessionFactory = sessionFactory;
	// }

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.sovzond.mgis2.dataaccess.base.IDAOBase#persist(T)
	 */
	@Override
	public void persist(T entity) {
		getSession().persist(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.sovzond.mgis2.dataaccess.base.IDAOBase#delete(T)
	 */
	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	public void merge(T entity) {
		getSession().merge(entity);
	}
}
