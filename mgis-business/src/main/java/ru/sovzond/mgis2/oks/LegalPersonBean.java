package ru.sovzond.mgis2.oks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.registers.LegalPersonDao;
import ru.sovzond.mgis2.registers.persons.LegalPerson;

import java.util.stream.Collectors;

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
		Pageable<LegalPerson> pager = dao.pager(dao.newPagerBuilder(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(LegalPerson::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
