package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.characteristics.ConstructionCharacteristics;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 14/11/15.
 */
@Service
public class ConstructCharacteristicsBean extends CRUDBeanBase<ConstructionCharacteristics> {

	@Autowired
	private ConstructCharacteristicsDao dao;

	@Override
	protected IPageableDAOBase<ConstructionCharacteristics> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstructionCharacteristics> getIIdentifiableDao() {
		return dao;
	}

}
