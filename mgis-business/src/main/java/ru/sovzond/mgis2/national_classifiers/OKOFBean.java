package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.registers.national_classifiers.OKOF;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 10.11.15.
 */
@Service
public class OKOFBean extends CRUDBeanBase<OKOF> {

	@Autowired
	private OKOFDao dao;

	@Override
	protected IPageableDAOBase<OKOF> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<OKOF> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<OKOF> list(String code, String name, String[] codeTemplates, String orderBy, int first, int max) {
		Pageable<OKOF> pager = dao.pager(dao.createFilter(code, name, codeTemplates, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(OKOF::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
