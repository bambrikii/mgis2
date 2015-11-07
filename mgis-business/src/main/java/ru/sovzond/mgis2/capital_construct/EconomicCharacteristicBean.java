package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.characteristics.economical.EconomicCharacteristic;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
@Service
public class EconomicCharacteristicBean extends CRUDBeanBase<EconomicCharacteristic> {

	@Autowired
	private EconomicCharacteristicDao dao;

	@Override
	protected IPageableDAOBase<EconomicCharacteristic> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<EconomicCharacteristic> getIIdentifiableDao() {
		return dao;
	}
}
