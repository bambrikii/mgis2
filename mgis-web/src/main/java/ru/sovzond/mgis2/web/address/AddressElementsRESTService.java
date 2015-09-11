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
												   @RequestParam(defaultValue = "") String subject,
												   @RequestParam(defaultValue = "") String region) {
		return addressElemsBean.region(orderBy, first, max, subject, region);
	}

	@RequestMapping(value = "/locality", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<KLADRLocality> locality(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
													 @RequestParam(defaultValue = "0") int first,
													 @RequestParam(defaultValue = "0") int max,
													 @RequestParam(defaultValue = "") String subject,
													 @RequestParam(defaultValue = "") String region,
													 @RequestParam(defaultValue = "") String locality) {
		return addressElemsBean.locality(orderBy, first, max, subject, region, locality);
	}

	@RequestMapping(value = "/street", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<KLADRStreet> street(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
												 @RequestParam(defaultValue = "0") int first,
												 @RequestParam(defaultValue = "0") int max,
												 @RequestParam(defaultValue = "") String subject,
												 @RequestParam(defaultValue = "") String region,
												 @RequestParam(defaultValue = "") String locality,
												 @RequestParam(defaultValue = "") String street) {
		return addressElemsBean.street(orderBy, first, max, subject, region, locality, street);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<String> home(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
										  @RequestParam(defaultValue = "0") int first,
										  @RequestParam(defaultValue = "0") int max,
										  @RequestParam(defaultValue = "") String subject,
										  @RequestParam(defaultValue = "") String region,
										  @RequestParam(defaultValue = "") String locality,
										  @RequestParam(defaultValue = "") String street,
										  @RequestParam(defaultValue = "") String home) {
		return addressElemsBean.home(orderBy, first, max, street, home);
	}

	@RequestMapping(value = "/housing", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<String> housing(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
											 @RequestParam(defaultValue = "0") int first,
											 @RequestParam(defaultValue = "0") int max,
											 @RequestParam(defaultValue = "") String subject,
											 @RequestParam(defaultValue = "") String region,
											 @RequestParam(defaultValue = "") String locality,
											 @RequestParam(defaultValue = "") String street,
											 @RequestParam(defaultValue = "") String home,
											 @RequestParam(defaultValue = "") String housing) {
		return addressElemsBean.housing(orderBy, first, max, street, home, housing);
	}

	@RequestMapping(value = "/building", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<String> building(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
											  @RequestParam(defaultValue = "0") int first,
											  @RequestParam(defaultValue = "0") int max,
											  @RequestParam(defaultValue = "") String subject,
											  @RequestParam(defaultValue = "") String region,
											  @RequestParam(defaultValue = "") String locality,
											  @RequestParam(defaultValue = "") String street,
											  @RequestParam(defaultValue = "") String home,
											  @RequestParam(defaultValue = "") String housing,
											  @RequestParam(defaultValue = "") String building) {
		return addressElemsBean.building(orderBy, first, max, subject, region, locality, street, home, housing, building);
	}

	@RequestMapping(value = "/apartment", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<String> apartment(@RequestParam(value = "orderBy", defaultValue = "") String orderBy,
											   @RequestParam(defaultValue = "0") int first,
											   @RequestParam(defaultValue = "0") int max,
											   @RequestParam(defaultValue = "") String subject,
											   @RequestParam(defaultValue = "") String region,
											   @RequestParam(defaultValue = "") String locality,
											   @RequestParam(defaultValue = "") String street,
											   @RequestParam(defaultValue = "") String home,
											   @RequestParam(defaultValue = "") String housing,
											   @RequestParam(defaultValue = "") String building,
											   @RequestParam(defaultValue = "") String apartment) {
		return addressElemsBean.apartment(orderBy, first, max, subject, region, locality, street, home, housing, building, apartment);
	}
}
