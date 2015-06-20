package ru.sovzond.mgis2.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.authentication.dataaccess.IPrivilegeDAO;
import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by asd on 20/06/15.
 */
@Service
public class PrivilegeBean extends CRUDBeanBase<Privilege> {

	@Autowired
	private IPrivilegeDAO privilegeDAO;

	@Override
	protected IPageableDAOBase<Privilege> getPageableDao() {
		return privilegeDAO;
	}

	@Override
	protected IIdentifiableDao<Privilege> getIIdentifiableDao() {
		return privilegeDAO;
	}

}
