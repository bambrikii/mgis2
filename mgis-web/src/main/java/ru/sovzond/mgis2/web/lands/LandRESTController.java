package ru.sovzond.mgis2.web.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.lands.LandBean;
import ru.sovzond.mgis2.national_classifiers.LandAllowedUsageBean;
import ru.sovzond.mgis2.national_classifiers.LandCategoryBean;
import ru.sovzond.mgis2.national_classifiers.OKTMOBean;
import ru.sovzond.mgis2.national_classifiers.TerritorialZoneBean;
import ru.sovzond.mgis2.oks.AddressBean;
import ru.sovzond.mgis2.oks.PersonBean;
import ru.sovzond.mgis2.registers.lands.Land;

import javax.transaction.Transactional;
import java.io.Serializable;

@RestController
@RequestMapping("/lands/land")
@Scope("session")
public class LandRESTController implements Serializable {

	private static final long serialVersionUID = -1259104464434967077L;

	@Autowired
	private LandBean landBean;

	@Autowired
	private PersonBean personBean;

	@Autowired
	private LandAllowedUsageBean allowedUsageByDocumentBean;

	@Autowired
	private OKTMOBean oktmoBean;

	@Autowired
	private TerritorialZoneBean allowedUsageByTerritorialZoneBean;

	@Autowired
	private AddressBean addressBean;

	@Autowired
	private LandCategoryBean landCategoryBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Land> list(@RequestParam(value = "cadastralNumber", defaultValue = "") String cadastralNumber, @RequestParam(value = "orderBy", defaultValue = "") String orderBy,
										@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return landBean.list(cadastralNumber, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Land save(@PathVariable("id") Long id, @RequestBody Land land) {
		Land land2;
		if (id == 0) {
			land2 = new Land();
		} else {
			land2 = landBean.load(id);
		}
		land2.setCadastralNumber(land.getCadastralNumber());
		land2.setStateRealEstateCadastreaStaging(land.getStateRealEstateCadastreaStaging());
		if (land.getLandCategory() != null) {
			land2.setLandCategory(landCategoryBean.load(land.getLandCategory().getId()));
		}
//		land2.setLandAreas();
		if (land.getAllowedUsageByDictionary() != null) {
			land2.setAllowedUsageByDictionary(allowedUsageByDocumentBean.load(land.getAllowedUsageByDictionary().getId()));
		}
		land2.setAllowedUsageByDocument(land.getAllowedUsageByDocument());
		if (land.getAllowedUsageByTerritorialZone() != null) {
			land2.setAllowedUsageByTerritorialZone(allowedUsageByTerritorialZoneBean.load(land.getAllowedUsageByTerritorialZone().getId()));
		}
		if (land.getAddressOfMunicipalEntity() != null) {
			land2.setAddressOfMunicipalEntity(oktmoBean.load(land.getAddressOfMunicipalEntity().getId()));
		}
		land2.setAddressPlacement(land.getAddressPlacement());
		if (land.getAddress() != null) {
			land2.setAddress(addressBean.load(land.getAddress().getId()));
		}
		land2.setAddress(land.getAddress());
		landBean.save(land2);
		return land2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public Land read(@PathVariable("id") Long id) {
		return landBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		landBean.remove(landBean.load(id));
	}

}
