package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.ConstructType;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 12/11/15.
 */
@Service
public class ConstructTypeBean extends CRUDBeanBase<ConstructType> {

	@Autowired
	private ConstructTypeDao dao;

	@Override
	protected IPageableDAOBase<ConstructType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstructType> getIIdentifiableDao() {
		return dao;
	}

	@Override
	public PageableContainer<ConstructType> list(String orderBy, int first, int max) {
		PageableContainer<ConstructType> pager = super.list(orderBy, first, max);
		return new PageableContainer<>(pager.getList().stream().map(ConstructType::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), pager.getCurrentPosition(), pager.getItemsPerPage());
	}
}
