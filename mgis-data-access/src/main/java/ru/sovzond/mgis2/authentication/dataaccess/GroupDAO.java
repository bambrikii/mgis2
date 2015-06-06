package ru.sovzond.mgis2.authentication.dataaccess;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.authentication.model.Group;
import ru.sovzond.mgis2.dataaccess.base.PageableDAOBase;

@Repository
public class GroupDAO extends PageableDAOBase<Group> implements IGroupDAO {

	@Override
	public Group findByName(String groupname) {
		Criteria criteria = getSession().createCriteria(persistentClass).add(Restrictions.eq("groupname", groupname));
		return (Group) criteria.uniqueResult();
	}
}
