package ru.sovzond.mgis2.authentication.dataaccess.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.authentication.dataaccess.IGroupDAO;
import ru.sovzond.mgis2.authentication.model.Group;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableDAOBase;

import java.util.List;

@Repository
public class GroupDAO extends PageableDAOBase<Group> implements IGroupDAO {

	public static final String ID = "id";
	public static final String GROUPNAME = "groupname";

	@Override
	public Group findById(Long id) {
		return (Group) createCriteria().add(Restrictions.eq(ID, id)).uniqueResult();
	}

	@Override
	public List<Group> findByIds(List<Long> ids) {
		return createCriteria().add(Restrictions.in(ID, ids)).list();
	}

	@Override
	public Group findByName(String groupName) {
		return (Group) createCriteria().add(Restrictions.eq(GROUPNAME, groupName)).uniqueResult();
	}

}
