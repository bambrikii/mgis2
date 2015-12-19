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
		KLADRLocality subject = findSubject(filter);
		KLADRLocality region = findRegion(filter, subject);
		KLADRLocality locality = findLocality(filter, subject, region);
		KLADRStreet street = findStreet(filter, subject, region, locality);
		return dao.find(subject, region, locality, street, filter.getHome(), filter.getHousing(), filter.getBuilding(), filter.getApartment());
	}

	private KLADRStreet findStreet(AddressFilter filter, KLADRLocality subject, KLADRLocality region, KLADRLocality locality) {
		List<KLADRStreet> streets = kladrStreetDao.findStreet(locality.getCode(), filter.getStreet(), filter.getStreetType());
		int size = streets.size();
		switch (size) {
			case 1:
				return streets.get(0);
			default:
				throw streetNotFoundException(size, filter, subject, region, locality);
		}
	}

	private KLADRLocality findLocality(AddressFilter filter, KLADRLocality subject, KLADRLocality region) {
		List<KLADRLocality> localities = kladrLocalityDao.findLocality(region.getCode(), filter.getLocality(), filter.getLocalityType());
		int size = localities.size();
		switch (size) {
			case 1:
				return localities.get(0);
			default:
				throw localityNotFoundException(size, filter, subject, region);
		}
	}

	private KLADRLocality findRegion(AddressFilter filter, KLADRLocality subject) {
		List<KLADRLocality> regions = kladrLocalityDao.findRegion(subject != null ? subject.getCode() : null, filter.getRegion(), filter.getRegionType());
		int size = regions.size();
		switch (size) {
			case 1:
				return regions.get(0);
			default:
				throw regionNotFoundException(size, filter, subject);
		}
	}

	private KLADRLocality findSubject(AddressFilter filter) {
		if (filter.subject()) {
			List<KLADRLocality> subjects = kladrLocalityDao.findSubject(filter.getSubject(), filter.getSubjectType());
			int size = subjects.size();
			switch (size) {
				case 1:
					return subjects.get(0);
				default:
					throw subjectNotFoundException(size, filter);
			}
		} else if (filter.subjectCode()) {
			KLADRLocality subject = kladrLocalityDao.findSubjectByCode(filter.getSubjectCode());
			if (subject == null) {
				throw subjectNotFoundException(0, filter);
			}
			return subject;
		} else {
			throw new IllegalArgumentException("No Subject filter defined.");
		}
	}

	private IllegalArgumentException subjectNotFoundException(int size, AddressFilter filter) {
		return new IllegalArgumentException(size + " Subjects found: " + filter.getSubject() + ", " + filter.getSubjectType() + ", " + filter.getSubjectCode() + " .");
	}

	private IllegalArgumentException regionNotFoundException(int size, AddressFilter filter, KLADRLocality subject) {
		return new IllegalArgumentException(size + " Regions found: " + filter.getRegion() + ", " + filter.getRegionType() + ". Subject: " + subject.getName() + ", " + subject.getSocr() + " .");
	}

	private IllegalArgumentException localityNotFoundException(int size, AddressFilter filter, KLADRLocality subject, KLADRLocality region) {
		return new IllegalArgumentException(size + " Localities found: " + filter.getLocality() + ", " + filter.getLocalityType() + ". Subject: " + subject.getName() + ", " + subject.getSocr() + ". Region: " + region.getName() + ", " + region.getSocr() + " .");
	}

	private IllegalArgumentException streetNotFoundException(int size, AddressFilter filter, KLADRLocality subject, KLADRLocality region, KLADRLocality locality) {
		return new IllegalArgumentException(size + " Streets found: " + filter.getStreet() + ", " + filter.getStreetType() + ". Subject: " + subject.getCode() + ", " + subject.getName() + ", " + subject.getSocr() + ". Region: " + region.getCode() + ", " + region.getName() + ", " + region.getSocr() + ". Locality: " + locality.getCode() + ", " + locality.getName() + ", " + locality.getSocr());
	}
}
