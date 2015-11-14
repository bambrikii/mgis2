package ru.sovzond.mgis2.web.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.common.classifiers.ExecutivePersonBean;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.geo.SpatialDataBean;
import ru.sovzond.mgis2.geo.SpatialGroup;
import ru.sovzond.mgis2.isogd.business.DocumentBean;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.lands.*;
import ru.sovzond.mgis2.lands.characteristics.LandCharacteristics;
import ru.sovzond.mgis2.lands.control.LandControl;
import ru.sovzond.mgis2.lands.rights.LandRights;
import ru.sovzond.mgis2.national_classifiers.*;
import ru.sovzond.mgis2.persons.PersonBean;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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
	private OKFSBean landOwnershipFormBean;

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

	@Autowired
	private LandEncumbranceBean landEncumbranceBean;

	@Autowired
	private LandControlBean landControlBean;

	@Autowired
	private LandControlInspectionKindBean landControlInspectionKindBean;

	@Autowired
	private LandControlInspectionResonBean landControlInspectionReasonBean;

	@Autowired
	private LandControlInspectionResultAvailabilityOfViolationsBean landControlInspectionResultAvailabilityOfViolationsBean;

	@Autowired
	private LandControlInspectionSubjectBean landControlInspectionSubjectBean;

	@Autowired
	private LandControlInspectionTypeBean landControlInspectionTypeBean;

	@Autowired
	private ExecutivePersonBean executivePersonBean;

	@Autowired
	private LandAreaBean landAreaBean;

	@Autowired
	private LandAreaTypeBean landAreaTypeBean;

	@Autowired
	private DocumentBean documentBean;

	@Autowired
	private SpatialDataBean spatialDataBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Land> list(@RequestParam(value = "cadastralNumber", defaultValue = "") String cadastralNumber, @RequestParam(value = "orderBy", defaultValue = "") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "") String ids) {
		Long[] idsAsLong = null;
		if (ids != null && ids.length() > 0) {
			String[] idsAsString = ids.split(",");
			idsAsLong = new Long[idsAsString.length];
			for (int i = 0; i < idsAsString.length; i++) {
				String idAsString = idsAsString[i];
				if (idAsString != null && !"".equals(idAsString)) {
					idsAsLong[i] = Long.parseLong(idAsString);
				}
			}
		}
		PageableContainer<Land> pager = landBean.list(cadastralNumber, idsAsLong, orderBy, first, max);
		return pager;
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

		// Land area
		land2.getLandAreas().clear();
		for (LandArea landArea : land.getLandAreas()) {
			LandArea landArea2 = null;
			if (landArea.getId() == 0) {
				landArea2 = new LandArea();
			} else {
				landArea2 = landAreaBean.load(landArea.getId());
				landAreaBean.save(landArea2);
			}
			landArea2.setValue(landArea.getValue());
			landArea2.setLandAreaType(landAreaTypeBean.load(landArea.getLandAreaType().getId()));
			landAreaBean.save(landArea2);
			land2.getLandAreas().add(landArea2);
		}


		LandRights rights = land.getRights();
		if (rights != null) {
			LandRights rights2 = land2.getRights();
			if (rights2 == null) {
				rights2 = new LandRights();
				land2.setRights(rights2);
				landRightsBean.save(rights2);
			}
			rights2.setOwnershipForm(rights.getOwnershipForm() != null ? landOwnershipFormBean.load(rights.getOwnershipForm().getId()) : null);
			rights2.setRightKind(rights.getRightKind() != null ? landRightKindBean.load(rights.getRightKind().getId()) : null);
			rights2.setRightOwner(rights.getRightOwner() != null ? personBean.load(rights.getRightOwner().getId()) : null);
			rights2.setEncumbrance(rights.getEncumbrance() != null ? landEncumbranceBean.load(rights.getEncumbrance().getId()) : null);
			rights2.setObligations(rights.isObligations());
			rights2.setOwnershipDate(rights.getOwnershipDate());
			rights2.setTerminationDate(rights.getTerminationDate());
			rights2.setComment(rights.getComment());
			rights2.setShare(rights.getShare());
			rights2.setAnnualTax(rights.getAnnualTax());
			rights2.setTotalArea(rights.getTotalArea());
			// Registration documents
			rights2.getRegistrationDocuments().clear();
			if (rights.getRegistrationDocuments() != null && rights.getRegistrationDocuments().size() > 0) {
				List<Document> load = documentBean.load(rights.getRegistrationDocuments().stream().map(document -> document.getId()).collect(Collectors.toList()));
				rights2.getRegistrationDocuments().addAll(load);
			}
			// Documents Certifying Rights
			rights2.getDocumentsCertifyingRights().clear();
			if (rights.getDocumentsCertifyingRights() != null && rights.getDocumentsCertifyingRights().size() > 0) {
				List<Document> load = documentBean.load(rights.getDocumentsCertifyingRights().stream().map(document -> document.getId()).collect(Collectors.toList()));
				rights2.getDocumentsCertifyingRights().addAll(load);
			}
		}
		LandCharacteristics chars = land.getCharacteristics();
		if (chars != null) {
			LandCharacteristics chars2 = land2.getCharacteristics();
			if (chars2 == null) {
				chars2 = new LandCharacteristics();
				land2.setCharacteristics(chars2);
				landCharacteristicsBean.save(chars2);
			}
			chars2.setCadastralCost(chars.getCadastralCost());
			chars2.setSpecificIndexOfCadastralCost(chars.getSpecificIndexOfCadastralCost());
			chars2.setMarketCost(chars.getMarketCost());
			chars2.setMortgageValue(chars.getMortgageValue());
			chars2.setCadastralCostImplementationDate(chars.getCadastralCostImplementationDate());
			chars2.setMarketCostImplementationDate(chars.getMarketCostImplementationDate());
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

		LandControl control = land.getControl();
		if (control != null) {
			LandControl control2 = land2.getControl();
			if (control2 == null) {
				control2 = new LandControl();
				land2.setControl(control2);
				landControlBean.save(control2);
			}
			control2.setExecutivePerson(control.getExecutivePerson() != null ? executivePersonBean.load(control.getExecutivePerson().getId()) : null);
			control2.setInspectedPerson(control.getInspectedPerson() != null ? personBean.load(control.getInspectedPerson().getId()) : null);
			control2.setInspectionDate(control.getInspectionDate());
			control2.setInspectionKind(control.getInspectionKind() != null ? landControlInspectionKindBean.load(control.getInspectionKind().getId()) : null);
			control2.setInspectionReason(control.getInspectionReason() != null ? landControlInspectionReasonBean.load(control.getInspectionReason().getId()) : null);
			control2.setInspectionReasonDescription(control.getInspectionReasonDescription());
			control2.setInspectionResultAvailabilityOfViolations(control.getInspectionResultAvailabilityOfViolations() != null ? landControlInspectionResultAvailabilityOfViolationsBean.load(control.getInspectionResultAvailabilityOfViolations().getId()) : null);
			control2.setInspectionResultDescription(control.getInspectionResultDescription());
			control2.setInspectionSubject(control.getInspectionSubject() != null ? landControlInspectionSubjectBean.load(control.getInspectionSubject().getId()) : null);
			control2.setInspectionType(control.getInspectionType() != null ? landControlInspectionTypeBean.load(control.getInspectionType().getId()) : null);
			control2.setPenaltyAmount(control.getPenaltyAmount());
			control2.setTimelineForViolations(control.getTimelineForViolations());
		}

		SpatialGroup spatialGroup = land.getSpatialData();
		if (spatialGroup != null) {
			SpatialGroup spatialGroup2 = land2.getSpatialData();
			if (spatialGroup2 == null) {
				spatialGroup2 = new SpatialGroup();
			}
			spatialGroup2 = spatialDataBean.save(spatialGroup, spatialGroup2);
			land2.setSpatialData(spatialGroup2);
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

	@RequestMapping(value = "/{id}/spatial-attribute", method = RequestMethod.POST)
	@Transactional
	public boolean saveGeospatialAttribute(@PathVariable("id") Long id, @RequestBody(required = true) String wktString) {
		return landBean.saveGeospatialAttribute(id, wktString);
	}
}
