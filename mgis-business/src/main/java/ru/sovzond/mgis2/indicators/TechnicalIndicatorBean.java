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
public class TechnicalIndicatorBean extends CRUDBeanBase<TechnicalIndicator> {

	@Autowired
	private TechnicalIndicatorDao dao;

	@Override
	protected IPageableDAOBase<TechnicalIndicator> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<TechnicalIndicator> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<TechnicalIndicator> list(String orderBy, int first, int max) {
		PageableContainer<TechnicalIndicator> pager = super.list(orderBy, first, max);
		return new PageableContainer<>(pager.getList().stream().map(TechnicalIndicator::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), pager.getCurrentPosition(), pager.getItemsPerPage());
	}
}
