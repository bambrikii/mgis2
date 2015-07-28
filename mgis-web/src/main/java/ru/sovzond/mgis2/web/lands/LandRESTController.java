package ru.sovzond.mgis2.web.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.lands.AddressPlacementTypeBean;
import ru.sovzond.mgis2.lands.LandAllowedUsageByDocumentBean;
import ru.sovzond.mgis2.lands.LandAllowedUsageByTerritorialZoneBean;
import ru.sovzond.mgis2.lands.LandBean;
import ru.sovzond.mgis2.national_classifiers.OKTMOBean;
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
	private LandAllowedUsageByDocumentBean allowedUsageByDocumentBean;

	@Autowired
	private OKTMOBean oktmoBean;

	@Autowired
	private LandAllowedUsageByTerritorialZoneBean allowedUsageByTerritorialZoneBean;

	@Autowired
	private AddressPlacementTypeBean addressPlacementTypeBean;

	@Autowired
	private AddressBean addressBean;

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
//		land2.setLandAreas();
		if (land.getAllowedUsageByDictionary() != null) {
			land2.setAllowedUsageByDictionary(personBean.load(land.getAllowedUsageByDictionary().getId()));
		}
		if (land.getAllowedUsageByDocument() != null) {
			land2.setAllowedUsageByDocument(allowedUsageByDocumentBean.load(land.getAllowedUsageByDocument().getId()));
		}
		if (land.getAllowedUsageByTerritorialZone() != null) {
			land2.setAllowedUsageByTerritorialZone(allowedUsageByTerritorialZoneBean.load(land.getAllowedUsageByDocument().getId()));
		}
		if (land.getAddressOfMunicipalEntity() != null) {
			land2.setAddressOfMunicipalEntity(oktmoBean.load(land.getAddressOfMunicipalEntity().getId()));
		}
		if (land.getAddressOfPlacementType() != null) {
			land2.setAddressOfPlacementType(addressPlacementTypeBean.load(land.getAddressOfPlacementType().getId()));
		}
		if (land.getAddressOfPlacement() != null) {
			land2.setAddressOfPlacement(addressBean.load(land.getAddressOfPlacement().getId()));
		}
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
