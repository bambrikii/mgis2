package ru.sovzond.mgis2.web.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.kladr.KLADRLocalityBean;
import ru.sovzond.mgis2.kladr.KLADRStreetBean;
import ru.sovzond.mgis2.national_classifiers.OKATOBean;
import ru.sovzond.mgis2.national_classifiers.OKTMOBean;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 30/08/15.
 */
@RestController
@RequestMapping("/addresses")
@Scope("session")
public class AddressRESTService implements Serializable {
	@Autowired
	private AddressBean addressBean;

	@Autowired
	private KLADRLocalityBean kladrLocalityBean;

	@Autowired
	private KLADRStreetBean kladrStreetBean;

	@Autowired
	private OKTMOBean oktmoBean;

	@Autowired
	private OKATOBean okatoBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Address> list(@RequestParam(value = "orderBy", defaultValue = "") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "") String name) {
		return addressBean.list(orderBy, first, max, name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Address save(@PathVariable("id") Long id, @RequestBody Address address) {
		Address address1;
		if (id == 0) {
			address1 = new Address();
		} else {
			address1 = addressBean.load(id);
		}
		address1.setOktmo(address.getOktmo() != null ? oktmoBean.load(address.getOktmo().getId()) : null);
		address1.setKladr(address.getKladr() != null ? kladrLocalityBean.load(address.getKladr().getId()) : null);
		address1.setOkato(address.getOkato() != null ? okatoBean.load(address.getOkato().getId()) : null);
		address1.setPostalCode(address.getPostalCode());
		address1.setSubject(address.getSubject() != null ? kladrLocalityBean.load(address.getSubject().getId()) : null);
		address1.setRegion(address.getRegion() != null ? kladrLocalityBean.load(address.getRegion().getId()) : null);
		address1.setLocality(address.getLocality() != null ? kladrLocalityBean.load(address.getLocality().getId()) : null);
		address1.setStreet(address.getStreet() != null ? kladrStreetBean.load(address.getStreet().getId()) : null);
		address1.setHome(address.getHome());
		address1.setHousing(address.getHousing());
		address1.setBuilding(address.getBuilding());
		address1.setApartment(address.getApartment());
		return addressBean.save(address1).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public Address read(@PathVariable("id") Long id) {
		return addressBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		addressBean.remove(addressBean.load(id));
	}
}
