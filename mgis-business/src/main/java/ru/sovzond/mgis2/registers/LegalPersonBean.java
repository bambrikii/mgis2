package ru.sovzond.mgis2.registers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.registers.persons.LegalPerson;

/**
 * Created by Alexander Arakelyan on 11.08.15.
 */
@Service
public class LegalPersonBean extends CRUDBeanBase<LegalPerson> {

	@Autowired
	private LegalPersonDao dao;

	@Override
	protected IPageableDAOBase<LegalPerson> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LegalPerson> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<LegalPerson> list(String name, String orderBy, int first, int max) {
		return dao.buildPager(dao.createFilter(name, orderBy, first, max));
	}
}