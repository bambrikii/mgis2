package ru.sovzond.mgis2.integration.data_exchange.imp.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.address.AddressFilterBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.AddressDTO;
import ru.sovzond.mgis2.kladr.KLADRLocality;
import ru.sovzond.mgis2.kladr.KLADRLocalityDao;
import ru.sovzond.mgis2.kladr.KLADRStreet;
import ru.sovzond.mgis2.kladr.KLADRStreetDao;
import ru.sovzond.mgis2.national_classifiers.OKATOBean;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 25/12/15.
 */
@Service
public class AddressResolverBean {

	@Autowired
	private AddressBean addressBean;

	@Autowired
	private KLADRLocalityDao kladrLocalityDao;

	@Autowired
	private KLADRStreetDao kladrStreetDao;

	@Autowired
	private OKATOBean okatoBean;


	public Address resolveAddress(AddressDTO addressDTO) {
		AddressFilterBuilder filterBuilder = addressBean.createfilterBuilder();
		filterBuilder //
				.subjectCode(addressDTO.getRegion()) //
				.region(addressDTO.getDistrictName(), addressDTO.getDistrictType()) //
				.locality(addressDTO.getLocalityName(), addressDTO.getLocalityType()) //
				.street(addressDTO.getStreetName(), addressDTO.getStreetType()) //
				.home(addressDTO.getLevelValue()).housing(addressDTO.getLevelType()) //
		;
		List<Address> addresses = addressBean.find(filterBuilder.build());
		switch (addresses.size()) {
			case 0:
				return saveAddress(new Address(), addressDTO);
			case 1:
				return saveAddress(addresses.get(0), addressDTO);
			default:
				throw new UnsupportedOperationException("More than one address found for dto: " + addressDTO);
		}
	}

	private Address saveAddress(Address address, AddressDTO addressDTO) {
		address.setApartment(null);
		address.setBuilding(null);
		address.setHousing(null);
		address.setHousing(addressDTO.getLevelType());
		address.setHome(addressDTO.getLevelValue());

		KLADRLocality subject = kladrLocalityDao.findSubjectByCode(addressDTO.getRegion());
		KLADRLocality region = kladrLocalityDao.findRegion(subject.getCode(), addressDTO.getDistrictName(), addressDTO.getDistrictType()).get(0);
		KLADRLocality locality = kladrLocalityDao.findLocality(region.getCode(), addressDTO.getLocalityName(), addressDTO.getLocalityType()).get(0);
		KLADRStreet street = kladrStreetDao.findStreet(locality.getCode(), addressDTO.getStreetName(), addressDTO.getStreetType()).get(0);

		address.setSubject(subject);
		address.setRegion(region);
		address.setLocality(locality);
		address.setStreet(street);

		OKATO okato = okatoBean.findByCode(addressDTO.getOkato());
		address.setOkato(okato);

		address.setOther(addressDTO.getNote());
		addressBean.save(address);
		return address;
	}
}
