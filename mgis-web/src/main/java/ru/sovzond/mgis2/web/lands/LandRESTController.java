package ru.sovzond.mgis2.web.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.lands.LandBean;
import ru.sovzond.mgis2.lands.LandCharacteristicsBean;
import ru.sovzond.mgis2.lands.LandRightsBean;
import ru.sovzond.mgis2.lands.LandTypeOfEngineeringSupportAreaBean;
import ru.sovzond.mgis2.national_classifiers.*;
import ru.sovzond.mgis2.oks.AddressBean;
import ru.sovzond.mgis2.oks.PersonBean;
import ru.sovzond.mgis2.registers.lands.Land;
import ru.sovzond.mgis2.registers.lands.characteristics.LandCharacteristics;
import ru.sovzond.mgis2.registers.lands.rights.LandRights;

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

	@Autowired
	private LandOwnershipFormBean landOwnershipFormBean;

	@Autowired
	private LandRightKindBean landRightKindBean;

	@Autowired
	private LandRightsBean landRightsBean;

	@Autowired
	private LandCharacteristicsBean landCharacteristicsBean;

	@Autowired
	private OKATOBean okatoBean;

	@Autowired
	private LandTypeOfEngineeringSupportAreaBean typeOfEngineeringSupportAreaBean;

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
			landBean.save(land2);
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
		LandRights rights = land.getRights();
		if (rights != null) {
			LandRights rights2 = land2.getRights();
			if (rights2 == null) {
				rights2 = new LandRights();
				rights2.setLand(land2);
				land2.setRights(rights2);
				landRightsBean.save(rights2);
			}
			rights2.setOwnershipForm(rights.getOwnershipForm() != null ? landOwnershipFormBean.load(rights.getOwnershipForm().getId()) : null);
			rights2.setRightKind(rights.getRightKind() != null ? landRightKindBean.load(rights.getRightKind().getId()) : null);
			rights2.setRightOwner(rights.getRightOwner() != null ? personBean.load(rights.getRightOwner().getId()) : null);
			rights2.setEncumbrance(rights.isEncumbrance());
			rights2.setObligations(rights.isObligations());
			rights2.setOwnershipDate(rights.getOwnershipDate());
			rights2.setTerminationDate(rights.getTerminationDate());
			rights2.setComment(rights.getComment());
			rights2.setShare(rights.getShare());
			rights2.setAnnualTax(rights.getAnnualTax());
			rights2.setTotalArea(rights.getTotalArea());
		}
		LandCharacteristics chars = land.getCharacteristics();
		if (chars != null) {
			LandCharacteristics chars2 = land2.getCharacteristics();
			if (chars2 == null) {
				chars2 = new LandCharacteristics();
				chars2.setLand(land2);
				land2.setCharacteristics(chars2);
				landCharacteristicsBean.save(chars2);
			}
			chars2.setCadastralPrice(chars.getCadastralPrice());
			chars2.setSpecificIndexOfCadastralPrice(chars.getSpecificIndexOfCadastralPrice());
			chars2.setMarketPrice(chars.getMarketPrice());
			chars2.setMortgageValue(chars.getMortgageValue());
			chars2.setImplementationDate(chars.getImplementationDate());
			chars2.setTerminationDate(chars.getTerminationDate());
			chars2.setStandardCost(chars.getStandardCost());
			chars2.setTypeOfEngineeringSupportArea(chars.getTypeOfEngineeringSupportArea() != null ? typeOfEngineeringSupportAreaBean.load(chars.getTypeOfEngineeringSupportArea().getId()) : null);
			chars2.setDistanceToTheConnectionPoint(chars.getDistanceToTheConnectionPoint());
			chars2.setDistanceToTheConnectionPointLength(chars.getDistanceToTheConnectionPointLength());
			chars2.setDistanceToTheObjectsOfTransportInfrastructure(chars.getDistanceToTheObjectsOfTransportInfrastructure());
			chars2.setNearestMunicipalEntity(chars.getNearestMunicipalEntity() != null ? oktmoBean.load(chars.getNearestMunicipalEntity().getId()) : null);
			chars2.setDistanceToTheNearestMunicipalEntity(chars.getDistanceToTheNearestMunicipalEntity());
			chars2.setCountrySubject(chars.getCountrySubject() != null ? okatoBean.load(chars.getCountrySubject().getId()) : null);
			chars2.setDistanceToTheCountrySubjectCenter(chars.getDistanceToTheCountrySubjectCenter());
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
