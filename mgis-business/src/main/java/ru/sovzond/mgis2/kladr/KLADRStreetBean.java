package ru.sovzond.mgis2.kladr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 10.09.15.
 */
@Service
public class KLADRStreetBean extends CRUDBeanBase<KLADRStreet> {

	@Autowired
	private KLADRStreetDao dao;

	@Override
	protected IPageableDAOBase<KLADRStreet> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<KLADRStreet> getIIdentifiableDao() {
		return dao;
	}
}
