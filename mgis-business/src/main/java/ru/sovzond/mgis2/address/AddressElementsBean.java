package ru.sovzond.mgis2.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.kladr.*;
import ru.sovzond.mgis2.national_classifiers.OKATODao;
import ru.sovzond.mgis2.national_classifiers.OKTMODao;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

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

	@Autowired
	private OKTMODao oktmoDao;

	public PageableContainer<OKATO> okato(String orderBy, int first, int max, String name) {
		Pageable<OKATO> pager = okatoDao.pager(okatoDao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(OKATO::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<String> kladr(String orderBy, int first, int max, String name) {
		// TODO:
		return null;
	}

	public PageableContainer<OKTMO> oktmo(String orderBy, int first, int max, String name) {
		Pageable<OKTMO> pager = oktmoDao.pager(oktmoDao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(OKTMO::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<KLADRLocality> subject(String orderBy, int first, int max, String name) {
		Pageable<KLADRLocality> pager = kladrLocalityDao.pager(kladrLocalityDao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRLocality::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<KLADRLocality> region(String orderBy, int first, int max, String name) {
		Pageable<KLADRLocality> pager = kladrLocalityDao.pager(kladrLocalityDao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRLocality::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<KLADRLocality> city(String orderBy, int first, int max, String name) {
		Pageable<KLADRLocality> pager = kladrLocalityDao.pager(kladrLocalityDao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRLocality::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<KLADRLocality> district(String orderBy, int first, int max, String name) {
		Pageable<KLADRLocality> pager = kladrLocalityDao.pager(kladrLocalityDao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRLocality::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<KLADRLocality> sovietVillage(String orderBy, int first, int max, String name) {
		Pageable<KLADRLocality> pager = kladrLocalityDao.pager(kladrLocalityDao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRLocality::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<KLADRLocality> locality(String orderBy, int first, int max, String name) {
		Pageable<KLADRLocality> pager = kladrLocalityDao.pager(kladrLocalityDao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRLocality::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<KLADRStreet> street(String orderBy, int first, int max, String name) {
		Pageable<KLADRStreet> pager = kladrStreetDao.pager(kladrStreetDao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(KLADRStreet::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

	public PageableContainer<String> home(String orderBy, int first, int max, String name) {
//		Pageable<KLADRHome> pager = kladrHomeDao.pager(kladrHomeDao.createFilter(name, orderBy, first, max));
//		return new PageableContainer<>(pager.list().stream().map(KLADRHome::clone).collect(Collectors.toList()), pager.count(), first, max);
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public PageableContainer<String> housing(String orderBy, int first, int max, String name) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public PageableContainer<String> building(String orderBy, int first, int max, String name) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public PageableContainer<String> apartment(String orderBy, int first, int max, String name) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
