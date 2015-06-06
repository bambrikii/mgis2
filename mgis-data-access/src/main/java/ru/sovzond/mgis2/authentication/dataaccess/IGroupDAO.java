package ru.sovzond.mgis2.authentication.dataaccess;

import ru.sovzond.mgis2.authentication.model.Group;
import ru.sovzond.mgis2.dataaccess.base.IDAOBase;

public interface IGroupDAO extends IDAOBase<Group> {

	Group findByName(String groupname1);

}
