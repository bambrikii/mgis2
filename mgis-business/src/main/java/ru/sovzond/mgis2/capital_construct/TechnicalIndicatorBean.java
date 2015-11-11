package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.indicators.TechnicalIndicator;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

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
}
