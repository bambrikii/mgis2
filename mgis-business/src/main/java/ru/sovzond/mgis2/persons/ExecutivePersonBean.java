package ru.sovzond.mgis2.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.registers.persons.ExecutivePerson;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class ExecutivePersonBean extends CRUDBeanBase<ExecutivePerson> {

	@Autowired
	private ExecutivePersonDao dao;

	@Override
	protected IPageableDAOBase<ExecutivePerson> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ExecutivePerson> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<ExecutivePerson> list(String name, String orderBy, int first, int max) {
		Pageable<ExecutivePerson> pager = dao.pager(dao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(person -> person.clone()).collect(Collectors.toList()), pager.count(), first, max);
	}
}
