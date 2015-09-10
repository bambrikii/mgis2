package ru.sovzond.mgis2.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.registers.NaturalPersonDao;
import ru.sovzond.mgis2.registers.persons.NaturalPerson;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 30/08/15.
 */
@Service
public class NaturalPersonBean extends CRUDBeanBase<NaturalPerson> {
	@Autowired
	private NaturalPersonDao dao;

	@Override
	protected IPageableDAOBase<NaturalPerson> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<NaturalPerson> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<NaturalPerson> list(String name, String orderBy, int first, int max) {
		Pageable<NaturalPerson> pager = dao.pager(dao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(NaturalPerson::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
