package ru.sovzond.mgis2.authentication.dataaccess;

import ru.sovzond.mgis2.authentication.model.Group;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

public interface IGroupDAO extends IPageableDAOBase<Group>, IIdentifiableDao<Group> {

	Group findByName(String groupname);

}
