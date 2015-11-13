package ru.sovzond.mgis2.web.capital_constructs;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.capital_construct.CapitalConstructBean;
import ru.sovzond.mgis2.capital_construct.ConstructTypeBean;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruct;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.isogd.business.DocumentBean;
import ru.sovzond.mgis2.kladr.KLADRLocalityBean;
import ru.sovzond.mgis2.national_classifiers.LandRightKindBean;
import ru.sovzond.mgis2.national_classifiers.OKFSBean;
import ru.sovzond.mgis2.national_classifiers.OKOFBean;
import ru.sovzond.mgis2.persons.PersonBean;
import ru.sovzond.mgis2.property.PropertyRightsBean;
import ru.sovzond.mgis2.rights.PropertyRights;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 */
@RestController
@RequestMapping("/capital-constructs/constructs")
@Scope("session")
public class CapitalConstructRESTService {

	@Autowired
	private CapitalConstructBean capitalConstructBean;

	@Autowired
	private ConstructTypeBean constructTypeBean;

	@Autowired
	private KLADRLocalityBean kladrLocalityBean;

	@Autowired
	private AddressBean addressBean;

	@Autowired
	private PropertyRightsBean propertyRightsBean;

	@Autowired
	private DocumentBean documentBean;

	@Autowired
	private OKFSBean okfsBean;

	@Autowired
	private OKOFBean okofBean;

	@Autowired
	private LandRightKindBean landRightKindBean;

	@Autowired
	private PersonBean personBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<CapitalConstruct> list(@RequestParam(value = "orderBy", defaultValue = "id DESC") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return capitalConstructBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public CapitalConstruct save(@PathVariable Long id, @RequestBody CapitalConstruct capitalConstruct) {
		CapitalConstruct capitalConstruct2;
		if (id == 0) {
			capitalConstruct2 = new CapitalConstruct();
		} else {
			capitalConstruct2 = capitalConstructBean.load(id);
		}
		BeanUtils.copyProperties(capitalConstruct, capitalConstruct2, new String[]{ //
				"id", //
				"constructType", //
				"municipalEntity", //
				"address", //
				"rights", //
				"constructiveElements", //

		});
		capitalConstruct2.setType(capitalConstruct.getType() != null ? constructTypeBean.load(capitalConstruct.getType().getId()) : null);
		capitalConstruct2.setMunicipalEntity(capitalConstruct.getMunicipalEntity() != null ? kladrLocalityBean.load(capitalConstruct.getMunicipalEntity().getId()) : null);
		capitalConstruct2.setAddress(capitalConstruct.getAddress() != null ? addressBean.load(capitalConstruct.getAddress().getId()) : null);
		PropertyRights rights = capitalConstruct.getRights();
		// Rights
		PropertyRights rights2;
		if (rights == null || rights.getId() == null || rights.getId() == 0) {
			rights2 = new PropertyRights();
		} else {
			rights2 = propertyRightsBean.load(rights.getId());
		}
		if (rights != null) {
			rights2.setOwnershipDate(rights.getOwnershipDate());
			rights2.setShare(rights.getShare());
			rights2.setTerminationDate(rights.getTerminationDate());
			if (rights.getDocumentsCertifyingRights() != null || rights.getDocumentsCertifyingRights().size() > 0) {
				rights2.setDocumentsCertifyingRights(documentBean.load(rights.getDocumentsCertifyingRights().stream().map(document -> document.getId()).collect(Collectors.toList())));
			} else {
				rights2.getDocumentsCertifyingRights().clear();
			}
			rights2.setOwnershipForm(rights.getOwnershipForm() != null ? okfsBean.load(rights.getOwnershipForm().getId()) : null);
			if (rights.getRegistrationDocuments() != null || rights.getRegistrationDocuments().size() > 0) {
				rights2.setRegistrationDocuments(documentBean.load(rights.getRegistrationDocuments().stream().map(document -> document.getId()).collect(Collectors.toList())));
			} else {
				rights2.getRegistrationDocuments().clear();
			}
			rights2.setRightKind(rights.getRightKind() != null ? landRightKindBean.load(rights.getRightKind().getId()) : null);
			rights2.setRightOwner(rights.getRightOwner() != null ? personBean.load(rights.getRightOwner().getId()) : null);
		}
		capitalConstruct2.setRights(rights2);
		// Economic Characteristics
		if (capitalConstruct.getEconomicCharacteristics() != null && capitalConstruct.getEconomicCharacteristics().size() > 0) {
			capitalConstruct2.setEconomicCharacteristics(capitalConstruct.getEconomicCharacteristics());
		} else {
			capitalConstruct2.getEconomicCharacteristics().clear();
		}
		// Technical Characteristics
		if (capitalConstruct.getTechnicalCharacteristics() != null && capitalConstruct.getTechnicalCharacteristics().size() > 0) {
			capitalConstruct2.setTechnicalCharacteristics(capitalConstruct.getTechnicalCharacteristics());
		} else {
			capitalConstruct2.getTechnicalCharacteristics().clear();
		}
		// Consturctive Elements
		if (capitalConstruct.getConstructiveElements() != null && capitalConstruct.getConstructiveElements().size() > 0) {
			capitalConstruct2.setConstructiveElements(capitalConstruct.getConstructiveElements());
		} else {
			capitalConstruct2.getConstructiveElements().clear();
		}
		capitalConstructBean.save(capitalConstruct2);
		return capitalConstruct2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public CapitalConstruct read(@PathVariable Long id) {
		return capitalConstructBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		capitalConstructBean.remove(capitalConstructBean.load(id));
	}

}
