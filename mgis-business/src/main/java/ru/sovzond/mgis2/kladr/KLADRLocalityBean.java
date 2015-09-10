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
public class KLADRLocalityBean extends CRUDBeanBase<KLADRLocality> {

	@Autowired
	private KLADRLocalityDao dao;

	@Override
	protected IPageableDAOBase<KLADRLocality> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<KLADRLocality> getIIdentifiableDao() {
		return dao;
	}
}
