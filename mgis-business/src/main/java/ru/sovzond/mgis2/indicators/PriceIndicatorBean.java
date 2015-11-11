package ru.sovzond.mgis2.indicators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.indicators.PriceIndicator;
import ru.sovzond.mgis2.indicators.PriceIndicatorDao;

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
}
