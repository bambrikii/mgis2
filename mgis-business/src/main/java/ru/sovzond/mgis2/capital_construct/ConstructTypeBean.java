package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.ConstructionType;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 12/11/15.
 */
@Service
public class ConstructTypeBean extends CRUDBeanBase<ConstructionType> {

	@Autowired
	private ConstructTypeDao dao;

	@Override
	protected IPageableDAOBase<ConstructionType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstructionType> getIIdentifiableDao() {
		return dao;
	}

	@Override
	public PageableContainer<ConstructionType> list(String orderBy, int first, int max) {
		PageableContainer<ConstructionType> pager = super.list(orderBy, first, max);
		return new PageableContainer<>(pager.getList().stream().map(ConstructionType::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), pager.getCurrentPosition(), pager.getItemsPerPage());
	}
}
