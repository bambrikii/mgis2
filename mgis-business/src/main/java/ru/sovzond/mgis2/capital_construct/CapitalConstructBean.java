package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 */
@Service
public class CapitalConstructBean extends CRUDBeanBase<CapitalConstruction> {
	@Autowired
	private CapitalConstructDao dao;

	@Override
	protected IPageableDAOBase<CapitalConstruction> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<CapitalConstruction> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<CapitalConstruction> list(String cadastralNumber, String name, String orderBy, int first, int max) {
		Pageable<CapitalConstruction> pager = dao.pager(dao.createFilter(cadastralNumber, name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(CapitalConstruction::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
