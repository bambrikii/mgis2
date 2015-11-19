package ru.sovzond.mgis2.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 05.10.15.
 */
@Service
public class CoordinateSystemBean extends CRUDBeanBase<CoordinateSystem> {

	@Autowired
	private CoordinateSystemDao dao;

	@Override
	protected IPageableDAOBase<CoordinateSystem> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<CoordinateSystem> getIIdentifiableDao() {
		return dao;
	}

	public CoordinateSystem findByName(String name) {
		return findByCode(name);
	}

	public CoordinateSystem findByCode(String code) {
		return dao.findByCode(code);
	}
}
