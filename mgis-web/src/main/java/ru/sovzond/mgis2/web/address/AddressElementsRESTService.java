package ru.sovzond.mgis2.web.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sovzond.mgis2.address.AddressElementsBean;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.kladr.KLADRLocality;
import ru.sovzond.mgis2.kladr.KLADRStreet;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 30/08/15.
 */
@RestController
@RequestMapping("/addresses/elements")
@Scope("session")
public class AddressElementsRESTService implements Serializable {
	@Autowired
	private AddressElementsBean addressElemsBean;

	@RequestMapping(value = "/okato", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<OKATO> okato(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
										  @RequestParam(defaultValue = "0") int first,
										  @RequestParam(defaultValue = "0") int max,
										  @RequestParam(defaultValue = "") String okato) {
		return addressElemsBean.okato(orderBy, first, max, okato);
	}

	@RequestMapping(value = "/kladr", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<String> kladr(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
										   @RequestParam(defaultValue = "0") int first,
										   @RequestParam(defaultValue = "0") int max,
										   @RequestParam(defaultValue = "") String kladr) {
		return addressElemsBean.kladr(orderBy, first, max, kladr);
	}

	@RequestMapping(value = "/oktmo", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<OKTMO> oktmo(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
										  @RequestParam(defaultValue = "0") int first,
										  @RequestParam(defaultValue = "0") int max,
										  @RequestParam(defaultValue = "") String oktmo) {
		return addressElemsBean.oktmo(orderBy, first, max, oktmo);
	}

	@RequestMapping(value = "/subject", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<KLADRLocality> subject(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
													@RequestParam(defaultValue = "0") int first,
													@RequestParam(defaultValue = "0") int max,
													@RequestParam(defaultValue = "") String subject) {
		return addressElemsBean.subject(orderBy, first, max, subject);
	}

	@RequestMapping(value = "/region", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<KLADRLocality> region(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
												   @RequestParam(defaultValue = "0") int first,
												   @RequestParam(defaultValue = "0") int max,
												   @RequestParam(defaultValue = "") String region) {
		return addressElemsBean.region(orderBy, first, max, region);
	}

	@RequestMapping(value = "/city", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<KLADRLocality> city(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
												 @RequestParam(defaultValue = "0") int first,
												 @RequestParam(defaultValue = "0") int max,
												 @RequestParam(defaultValue = "") String city) {
		return addressElemsBean.city(orderBy, first, max, city);
	}

	@RequestMapping(value = "/district", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<KLADRLocality> district(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
													 @RequestParam(defaultValue = "0") int first,
													 @RequestParam(defaultValue = "0") int max,
													 @RequestParam(defaultValue = "") String district) {
		return addressElemsBean.district(orderBy, first, max, district);
	}

	@RequestMapping(value = "/soviet-village", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<KLADRLocality> sovietVillage(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
														  @RequestParam(defaultValue = "0") int first,
														  @RequestParam(defaultValue = "0") int max,
														  @RequestParam(defaultValue = "") String sovietVillage) {
		return addressElemsBean.sovietVillage(orderBy, first, max, sovietVillage);
	}

	@RequestMapping(value = "/locality", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<KLADRLocality> locality(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
													 @RequestParam(defaultValue = "0") int first,
													 @RequestParam(defaultValue = "0") int max,
													 @RequestParam(defaultValue = "") String locality) {
		return addressElemsBean.locality(orderBy, first, max, locality);
	}

	@RequestMapping(value = "/street", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<KLADRStreet> street(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
												 @RequestParam(defaultValue = "0") int first,
												 @RequestParam(defaultValue = "0") int max,
												 @RequestParam(defaultValue = "") String street) {
		return addressElemsBean.street(orderBy, first, max, street);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<String> home(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
										  @RequestParam(defaultValue = "0") int first,
										  @RequestParam(defaultValue = "0") int max,
										  @RequestParam(defaultValue = "") String home) {
		return addressElemsBean.home(orderBy, first, max, home);
	}

	@RequestMapping(value = "/housing", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<String> housing(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
											 @RequestParam(defaultValue = "0") int first,
											 @RequestParam(defaultValue = "0") int max,
											 @RequestParam(defaultValue = "") String housing) {
		return addressElemsBean.housing(orderBy, first, max, housing);
	}

	@RequestMapping(value = "/building", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<String> building(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
											  @RequestParam(defaultValue = "0") int first,
											  @RequestParam(defaultValue = "0") int max,
											  @RequestParam(defaultValue = "") String building) {
		return addressElemsBean.building(orderBy, first, max, building);
	}

	@RequestMapping(value = "/apartment", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<String> apartment(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
											   @RequestParam(defaultValue = "0") int first,
											   @RequestParam(defaultValue = "0") int max,
											   @RequestParam(defaultValue = "") String apartment) {
		return addressElemsBean.apartment(orderBy, first, max, apartment);
	}
}