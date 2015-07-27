package ru.sovzond.mgis2.oks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.oks.Address;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Service
public class AddressBean extends CRUDBeanBase<Address> {

	@Autowired
	private ru.sovzond.mgis2.registers.oks.AddressDao dao;


	@Override
	protected IPageableDAOBase<Address> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Address> getIIdentifiableDao() {
		return dao;
	}
}
