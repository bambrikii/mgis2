package ru.sovzond.mgis2.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.kladr.KLADRLocality;
import ru.sovzond.mgis2.kladr.KLADRLocalityDao;
import ru.sovzond.mgis2.kladr.KLADRStreet;
import ru.sovzond.mgis2.kladr.KLADRStreetDao;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Service
public class AddressBean extends CRUDBeanBase<Address> {

	@Autowired
	private AddressDao dao;

	@Autowired
	private KLADRLocalityDao kladrLocalityDao;

	@Autowired
	private KLADRStreetDao kladrStreetDao;

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

	public List<Address> find(AddressFilter filter) {
		KLADRLocality subject = null;
		if (filter.subject()) {
			subject = kladrLocalityDao.findSubject(filter.getSubject(), filter.getSubjectType()).get(0);
		} else if (filter.subjectCode()) {
			subject = kladrLocalityDao.findSubjectByCode(filter.getSubjectCode());
		}
		KLADRLocality region = kladrLocalityDao.findRegion(subject != null ? subject.getCode() : null, filter.getRegion(), filter.getRegionType()).get(0);
		KLADRLocality locality = kladrLocalityDao.findLocality(region.getCode(), filter.getLocality(), filter.getLocalityType()).get(0);
		List<KLADRStreet> streets = kladrStreetDao.findStreet(locality.getCode(), filter.getStreet(), filter.getStreetType());
		KLADRStreet street = null;
		switch (streets.size()) {
			case 1:
				street = streets.get(0);
				break;
			default:
				throw new IllegalArgumentException("No street found: " + filter.getStreet() + ", " + filter.getStreetType());
		}
		return dao.find(subject, region, locality, street, filter.getHome(), filter.getHousing(), filter.getBuilding(), filter.getApartment());
	}
}
