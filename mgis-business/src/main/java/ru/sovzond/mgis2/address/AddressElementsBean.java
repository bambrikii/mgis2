package ru.sovzond.mgis2.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.kladr.*;
import ru.sovzond.mgis2.national_classifiers.OKATODao;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 10.09.15.
 */
@Service
public class AddressElementsBean {

	@Autowired
	private OKATODao okatoDao;

	@Autowired
	private KLADRLocalityDao kladrLocalityDao;

	@Autowired
	private KLADRStreetDao kladrStreetDao;

	@Autowired
	private KLADRHomeDao kladrHomeDao;

	public PageableContainer<KLADRLocality> subject(String orderBy, int first, int max, String name) {
		Pageable<KLADRLocality> pager = kladrLocalityDao.pager(kladrLocalityDao.createSubjectFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRLocality::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<KLADRLocality> region(String orderBy, int first, int max, String subject, String region) {
		Pageable<KLADRLocality> pager = kladrLocalityDao.pager(kladrLocalityDao.createRegionFilter(subject, region, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRLocality::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<KLADRLocality> locality(String orderBy, int first, int max, String subjectCode, String regionCode, String localityName) {
		Pageable<KLADRLocality> pager = kladrLocalityDao.pager(kladrLocalityDao.createLocalityFilter(subjectCode, regionCode, localityName, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRLocality::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<KLADRStreet> street(String orderBy, int first, int max, String subjectCode, String regionCode, String localityCode, String streetName) {
		Pageable<KLADRStreet> pager = kladrStreetDao.pager(kladrStreetDao.createFilter(subjectCode, regionCode, localityCode, streetName, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRStreet::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<String> home(String orderBy, int first, int max, String streetCode, String homeName) {
		Pageable<KLADRHome> pager = kladrHomeDao.pager(kladrHomeDao.createHomeFilter(streetCode, homeName, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(home -> home.getName()).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<String> housing(String orderBy, int first, int max, String streetCode, String homeName, String housingName) {
		Pageable<KLADRHome> pager = kladrHomeDao.pager(kladrHomeDao.createHousingFilter(streetCode, homeName, housingName, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(home -> home.getName()).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<String> building(String orderBy, int first, int max, String subject, String region, String locality, String street, String home, String housing, String name) {
		// TODO:
		return new PageableContainer<>(Collections.emptyList(), 0, first, max);
	}

	public PageableContainer<String> apartment(String orderBy, int first, int max, String subject, String region, String locality, String street, String home, String housing, String building, String name) {
		// TODO:
		return new PageableContainer<>(Collections.emptyList(), 0, first, max);
	}
}
