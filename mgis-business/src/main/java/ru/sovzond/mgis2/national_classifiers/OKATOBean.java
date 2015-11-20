package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@Service
public class OKATOBean extends CRUDBeanBase<OKATO> {

	@Autowired
	private OKATODao dao;

	@Override
	protected IPageableDAOBase<OKATO> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<OKATO> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<OKATO> list(String orderBy, int first, int max, String code, String name) {
		Pageable<OKATO> pager = dao.pager(dao.createFilter(code, name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(OKATO::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public OKATO findByCode(String code) {
		return dao.findByCode(code);
	}
}
