package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.registers.national_classifiers.OKOPF;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 14.09.15.
 */
@Service
public class OKOPFBean extends CRUDBeanBase<OKOPF> {

	@Autowired
	private OKOPFDao dao;

	@Override
	protected IPageableDAOBase<OKOPF> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<OKOPF> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<OKOPF> list(String orderBy, int first, int max, String name) {
		Pageable<OKOPF> pager = dao.pager(dao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(OKOPF::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
