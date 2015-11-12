package ru.sovzond.mgis2.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
@Service
public class PriceIndicatorBean extends CRUDBeanBase<PriceIndicator> {

	@Autowired
	private PriceIndicatorDao dao;

	@Override
	protected IPageableDAOBase<PriceIndicator> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<PriceIndicator> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<PriceIndicator> list(String orderBy, int first, int max) {
		PageableContainer<PriceIndicator> pager = super.list(orderBy, first, max);
		return new PageableContainer<>(pager.getList().stream().map(PriceIndicator::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), pager.getCurrentPosition(), pager.getItemsPerPage());
	}
}
