package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.registers.national_classifiers.OKOGU;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 14.09.15.
 */
@Service
public class OKOGUBean extends CRUDBeanBase<OKOGU> {

	@Autowired
	private OKOGUDao dao;

	@Override
	protected IPageableDAOBase<OKOGU> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<OKOGU> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<OKOGU> list(String orderBy, int first, int max, String name) {
		Pageable<OKOGU> pager = dao.pager(dao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(OKOGU::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
