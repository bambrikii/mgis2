package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.registers.national_classifiers.OKEI;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 10.11.15.
 */
@Service
public class OKEIBean extends CRUDBeanBase<OKEI> {

	@Autowired
	private OKEIDao dao;

	@Override
	protected IPageableDAOBase<OKEI> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<OKEI> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<OKEI> list(String name, String orderBy, int first, int max) {
		Pageable<OKEI> pager = dao.pager(dao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(OKEI::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
