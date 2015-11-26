package ru.sovzond.mgis2.address;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.sovzond.mgis2.business.base.HibernateConfiguration;
import ru.sovzond.mgis2.kladr.KLADRLocality;
import ru.sovzond.mgis2.kladr.KLADRLocalityDao;
import ru.sovzond.mgis2.kladr.KLADRStreet;
import ru.sovzond.mgis2.kladr.KLADRStreetDao;
import ru.sovzond.mgis2.national_classifiers.OKATOBean;
import ru.sovzond.mgis2.national_classifiers.OKTMOBean;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 20.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class AddressBeanTest {

	@Autowired
	private AddressBean addressBean;

	@Autowired
	private OKATOBean okatoBean;

	@Autowired
	private OKTMOBean oktmoBean;

	@Autowired
	private KLADRLocalityDao kladrLocalityDao;

	@Autowired
	private KLADRStreetDao kladrStreetDao;

	@Test
	@Transactional
	public void testAddress() {
		String fakeApartment1 = "fakeApartment1";
		String fakeBuilding1 = "fakeBuilding1";
		String fakeHome1 = "fakeHome1";
		String fakeHousing1 = "fakeHousing1";
		String fakeNote1 = "fakeNote1";
		String fakeOther1 = "fakeOther1";
		String fakePostalCode1 = "fakePostalCode1";

		String subjectName = "Адыгея";
		String subjectType = "Респ";
		String regionName = "Майкоп";
		String regionType = "г";
		String localityName = "Веселый";
		String localityType = "х";
		String streetName = "Веселая";
		String streetType = "ул";

		Address address = new Address();
		address.setApartment(fakeApartment1);
		address.setBuilding(fakeBuilding1);
		address.setHome(fakeHome1);
		address.setHousing(fakeHousing1);


		KLADRLocality subject = kladrLocalityDao.findSubject(subjectName, subjectType).get(0);
		KLADRLocality region = kladrLocalityDao.findRegion(subject.getCode(), regionName, regionType).get(0);
		KLADRLocality locality = kladrLocalityDao.findLocality(region.getCode(), localityName, localityType).get(0);
		KLADRStreet street = kladrStreetDao.findStreet(locality.getCode(), streetName, streetType).get(0);

		address.setSubject(subject);
		address.setRegion(region);
		address.setLocality(locality);
		address.setStreet(street);

		OKATO okato = okatoBean.findByCode("79");
		address.setOkato(okato);

		OKTMO oktmo = oktmoBean.list("79000000", null, null, 0, 0).getList().get(0);
		address.setOktmo(oktmo);

		address.setOther(fakeOther1);
		address.setPostalCode(fakePostalCode1);
		addressBean.save(address);

		AddressFilterBuilder builder = new AddressFilterBuilder() //
				.subject(subjectName, subjectType) //
				.region(regionName, regionType) //
				.locality(localityName, localityType) //
				.street(streetName, streetType) //
				.home(fakeHome1) //
				.building(fakeBuilding1) //
				.housing(fakeHousing1) //
				.apartment(fakeApartment1) //
				;
		List<Address> addresses = addressBean.find(builder.build());
		int addressesFound = addresses.size();

		addressBean.remove(address);

		Assert.assertEquals(1, addressesFound);
	}
}
