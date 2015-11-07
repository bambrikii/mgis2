package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.characteristics.technical.TechnicalCharacteristic;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
@Service
public class TechnicalCharacteristicBean extends CRUDBeanBase<TechnicalCharacteristic> {

	@Autowired
	private TechnicalCharacteristicDao dao;


	@Override
	protected IPageableDAOBase<TechnicalCharacteristic> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<TechnicalCharacteristic> getIIdentifiableDao() {
		return dao;
	}
}
