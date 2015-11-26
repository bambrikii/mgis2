package ru.sovzond.mgis2.address;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sovzond.mgis2.dataaccess.base.HibernateConfiguration;
import ru.sovzond.mgis2.kladr.KLADRLocality;
import ru.sovzond.mgis2.kladr.KLADRLocalityDao;
import ru.sovzond.mgis2.kladr.KLADRStreet;
import ru.sovzond.mgis2.kladr.KLADRStreetDao;
import ru.sovzond.mgis2.national_classifiers.OKATODao;
import ru.sovzond.mgis2.national_classifiers.OKTMODao;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 20.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class AddressDaoTest {

	@Autowired
	private AddressDao dao;

	@Autowired
	private KLADRLocalityDao kladrLocalityDao;

	@Autowired
	private KLADRStreetDao kladrStreetDao;

	@Autowired
	private OKATODao okatoDao;

	@Autowired
	private OKTMODao oktmoDao;

	@Test
	public void testAddress() {
		String fakeApartment1 = "fakeApartment1";
		String fakeBuilding1 = "fakeBuilding1";
		String fakeHome1 = "fakeHome1";
		String fakeHousing1 = "fakeHousing1";
		String fakeNote1 = "fakeNote1";
		String fakeOther1 = "fakeOther1";
		String fakePostalCode1 = "fakePostalCode1";

		String subjectName = "0100000000000";
		String regionName = "0100000100000";
		String localityName = "0100000100200";
		String streetName = "01000001002000400";

		Address address = new Address();
		address.setApartment(fakeApartment1);
		address.setBuilding(fakeBuilding1);
		address.setHome(fakeHome1);
		address.setHousing(fakeHousing1);

		KLADRLocality subject = kladrLocalityDao.pager(kladrLocalityDao.createSubjectFilter(subjectName, null, 0, 0)).list().get(0);
		address.setSubject(subject);

		KLADRLocality region = kladrLocalityDao.pager(kladrLocalityDao.createRegionFilter(subject.getCode(), regionName, null, 0, 0)).list().get(0);
		address.setRegion(region);

		KLADRLocality locality = kladrLocalityDao.pager(kladrLocalityDao.createLocalityFilter(subject.getCode(), region.getCode(), localityName, null, 0, 0)).list().get(0);
		address.setLocality(locality);

		KLADRStreet street = kladrStreetDao.pager(kladrStreetDao.createFilter(subject.getCode(), region.getCode(), locality.getCode(), streetName, null, 0, 0)).list().get(0);
		address.setStreet(street);

		address.setNote(fakeNote1);

		OKATO okato = okatoDao.pager(okatoDao.createFilter("79", null, null, 0, 0)).list().get(0);
		address.setOkato(okato);

		OKTMO oktmo = oktmoDao.pager(oktmoDao.createFilter("79000000", null, null, 0, 0)).list().get(0);
		address.setOktmo(oktmo);

		address.setOther(fakeOther1);
		address.setPostalCode(fakePostalCode1);
		dao.save(address);

		AddressFilterBuilder builder = new AddressFilterBuilder()
				.apartment(fakeApartment1)
				.building(fakeBuilding1)
//				.district(fa)
				.home(fakeHome1)
				.housing(fakeHousing1)
				.locality(locality.getName(), locality.getSocr())
				.region(region.getName(), region.getSocr())
				.street(street.getName(), street.getSocr())
				.subject(subject.getName(), subject.getSocr());
		List<Address> addresses = dao.find(builder.build());
		int addressesFound = addresses.size();

		dao.delete(address);

		Assert.assertEquals(1, addressesFound);
	}
}
