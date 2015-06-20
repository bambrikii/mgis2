package ru.sovzond.mgis2.authentication.dataaccess;

import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

public interface IPrivilegeDAO extends IPageableDAOBase<Privilege>, IIdentifiableDao<Privilege> {

	Privilege findByName(String privName);

}
