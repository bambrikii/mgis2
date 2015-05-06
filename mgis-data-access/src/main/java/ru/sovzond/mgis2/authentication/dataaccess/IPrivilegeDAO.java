package ru.sovzond.mgis2.authentication.dataaccess;

import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.dataaccess.base.IDAOBase;

public interface IPrivilegeDAO extends IDAOBase<Privilege> {

	Privilege findByName(String privName);

}