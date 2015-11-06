package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.lands.characteristics.LandCharacteristicsEngineeringSupportArea;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class LandTypeOfEngineeringSupportAreaBean extends CRUDBeanBase<LandCharacteristicsEngineeringSupportArea> {
	@Autowired
	private LandTypeOfEngineeringSupportAreaDao dao;

	@Override
	protected IPageableDAOBase<LandCharacteristicsEngineeringSupportArea> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandCharacteristicsEngineeringSupportArea> getIIdentifiableDao() {
		return dao;
	}
}
