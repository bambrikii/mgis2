package ru.sovzond.mgis2.oks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.oks.PersonDao;
import ru.sovzond.mgis2.registers.oks.rights.Person;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Service
public class PersonBean extends CRUDBeanBase<Person> {
	@Autowired
	private PersonDao dao;

	@Override
	protected IPageableDAOBase<Person> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Person> getIIdentifiableDao() {
		return dao;
	}
}
