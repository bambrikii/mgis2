package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.lands.AddressPlacementType;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Service
public class AddressPlacementTypeBean extends CRUDBeanBase<AddressPlacementType> {

	@Autowired
	private AddressPlacementTypeDao dao;

	@Override
	protected IPageableDAOBase<AddressPlacementType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<AddressPlacementType> getIIdentifiableDao() {
		return dao;
	}
}
