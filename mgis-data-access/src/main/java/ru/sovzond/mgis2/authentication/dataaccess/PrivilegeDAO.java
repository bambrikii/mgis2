package ru.sovzond.mgis2.authentication.dataaccess;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.dataaccess.base.DAOBase;

@Repository
public class PrivilegeDAO extends DAOBase<Privilege> implements IPrivilegeDAO {

	@Override
	public Privilege findByName(String name) {
		Privilege result = (Privilege) getSession().createCriteria(Privilege.class).add(Restrictions.eq("name", name)).uniqueResult();
		return result;
	}
}
