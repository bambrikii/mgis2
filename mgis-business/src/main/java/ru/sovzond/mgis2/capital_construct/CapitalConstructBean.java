package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruct;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 */
@Service
public class CapitalConstructBean extends CRUDBeanBase<CapitalConstruct> {
	@Autowired
	private CapitalConstructDao dao;

	@Override
	protected IPageableDAOBase<CapitalConstruct> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<CapitalConstruct> getIIdentifiableDao() {
		return dao;
	}

	@Override
	public PageableContainer<CapitalConstruct> list(String orderBy, int first, int max) {
		PageableContainer<CapitalConstruct> pager = super.list(orderBy, first, max);
		return new PageableContainer<>(pager.getList().stream().map(CapitalConstruct::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), pager.getCurrentPosition(), pager.getItemsPerPage());
	}
}
