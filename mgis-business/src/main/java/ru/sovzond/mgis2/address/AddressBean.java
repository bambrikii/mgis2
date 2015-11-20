package ru.sovzond.mgis2.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Service
public class AddressBean extends CRUDBeanBase<Address> {

	@Autowired
	private AddressDao dao;


	@Override
	protected IPageableDAOBase<Address> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Address> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<Address> list(String orderBy, int first, int max, String name) {
		Pageable<Address> pager = dao.pager(dao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(Address::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public AddressFilterBuilder createfilterBuilder() {
		return new AddressFilterBuilder();
	}

	public List<Address> find(AddressFilter addressFilter) {
		return dao.find(addressFilter);
	}
}
