package ru.sovzond.mgis2.national_classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.characteristics.economical.PriceType;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
@Service
public class PriceTypeBean extends CRUDBeanBase<PriceType> {

	@Autowired
	private PriceTypeDao dao;

	@Override
	protected IPageableDAOBase<PriceType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<PriceType> getIIdentifiableDao() {
		return dao;
	}
}
