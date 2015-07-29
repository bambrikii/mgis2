package ru.sovzond.mgis2.authentication.dataaccess.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.authentication.dataaccess.IPrivilegeDAO;
import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;

import java.util.List;

@Repository
public class PrivilegeDAO extends CRUDDaoBase<Privilege> implements IPrivilegeDAO {

	public static final String ID = "id";
	public static final String NAME = "name";

	@Override
	public Privilege findById(Long id) {
		return (Privilege) createCriteria().add(Restrictions.eq(ID, id)).uniqueResult();
	}

	@Override
	public List<Privilege> findByIds(List<Long> ids) {
		return createCriteria().add(Restrictions.in(ID, ids)).list();
	}

	@Override
	public Privilege findByName(String privilegeName) {
		return (Privilege) createCriteria().add(Restrictions.eq(NAME, privilegeName)).uniqueResult();
	}

}
