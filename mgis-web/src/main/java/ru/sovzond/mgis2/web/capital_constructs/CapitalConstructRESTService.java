package ru.sovzond.mgis2.web.capital_constructs;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.capital_construct.*;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruct;
import ru.sovzond.mgis2.capital_constructs.characteristics.ConstructCharacteristics;
import ru.sovzond.mgis2.capital_constructs.characteristics.economical.EconomicCharacteristic;
import ru.sovzond.mgis2.capital_constructs.characteristics.technical.TechnicalCharacteristic;
import ru.sovzond.mgis2.capital_constructs.constructive_elements.ConstructiveElement;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.indicators.PriceIndicatorBean;
import ru.sovzond.mgis2.indicators.TechnicalIndicatorBean;
import ru.sovzond.mgis2.isogd.business.DocumentBean;
import ru.sovzond.mgis2.kladr.KLADRLocalityBean;
import ru.sovzond.mgis2.national_classifiers.LandRightKindBean;
import ru.sovzond.mgis2.national_classifiers.OKEIBean;
import ru.sovzond.mgis2.national_classifiers.OKFSBean;
import ru.sovzond.mgis2.national_classifiers.OKOFBean;
import ru.sovzond.mgis2.persons.PersonBean;
import ru.sovzond.mgis2.property.PropertyRightsBean;
import ru.sovzond.mgis2.rights.PropertyRights;

import javax.transaction.Transactional;
import java.util.*;
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

	@Autowired
	private ConstructCharacteristicsBean constructCharacteristicsBean;

	@Autowired
	private EconomicCharacteristicBean economicCharacteristicBean;

	@Autowired
	private TechnicalCharacteristicBean technicalCharacteristicBean;

	@Autowired
	private PriceIndicatorBean priceIndicatorBean;

	@Autowired
	private TechnicalIndicatorBean technicalIndicatorBean;

	@Autowired
	private ConstructiveElementBean constructiveElementBean;

	@Autowired
	private ConstructiveElementTypeBean constructiveElementTypeBean;

	@Autowired
	private OKEIBean okeiBean;

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
			if (rights.getDocumentsCertifyingRights() == null || rights.getDocumentsCertifyingRights().size() == 0) {
				rights2.getDocumentsCertifyingRights().clear();
			} else {
				rights2.setDocumentsCertifyingRights(documentBean.load(rights.getDocumentsCertifyingRights().stream().map(document -> document.getId()).collect(Collectors.toList())));
			}
			rights2.setOwnershipForm(rights.getOwnershipForm() != null ? okfsBean.load(rights.getOwnershipForm().getId()) : null);
			if (rights.getRegistrationDocuments() == null || rights.getRegistrationDocuments().size() == 0) {
				rights2.getRegistrationDocuments().clear();
			} else {
				rights2.setRegistrationDocuments(documentBean.load(rights.getRegistrationDocuments().stream().map(document -> document.getId()).collect(Collectors.toList())));
			}
			rights2.setRightKind(rights.getRightKind() != null ? landRightKindBean.load(rights.getRightKind().getId()) : null);
			rights2.setRightOwner(rights.getRightOwner() != null ? personBean.load(rights.getRightOwner().getId()) : null);
			propertyRightsBean.save(rights2);
		}
		capitalConstruct2.setRights(rights2);

		// Characteristics
		ConstructCharacteristics characteristics = capitalConstruct.getCharacteristics();
		if (characteristics != null) {
			ConstructCharacteristics characteristics2 = capitalConstruct2.getCharacteristics();
			if (characteristics2 == null || characteristics2.getId() == null || characteristics2.getId() == 0) {
				characteristics2 = new ConstructCharacteristics();
			} else {
				characteristics2 = constructCharacteristicsBean.load(characteristics.getId());
			}

			// Economic Characteristics
			if (characteristics.getEconomicCharacteristics() != null && characteristics.getEconomicCharacteristics().size() > 0) {
				syncEconomicCharacteristics(characteristics2.getEconomicCharacteristics(), characteristics.getEconomicCharacteristics());
			} else {
				characteristics2.getEconomicCharacteristics().forEach(economicCharacteristicBean::remove);
				characteristics2.getEconomicCharacteristics().clear();
			}
			constructCharacteristicsBean.save(characteristics2);
			// Technical Characteristics
			if (characteristics.getTechnicalCharacteristics() != null && characteristics.getTechnicalCharacteristics().size() > 0) {
				syncTechnicalCharacteristics(characteristics2.getTechnicalCharacteristics(), characteristics.getTechnicalCharacteristics());
			} else {
				characteristics2.getTechnicalCharacteristics().forEach(technicalCharacteristicBean::remove);
				characteristics2.getTechnicalCharacteristics().clear();
			}
			constructCharacteristicsBean.save(characteristics2);
			capitalConstruct2.setCharacteristics(characteristics2);
		}
		// Constructive Elements
		syncConstructiveElements(capitalConstruct2.getConstructiveElements(), capitalConstruct.getConstructiveElements());

		capitalConstructBean.save(capitalConstruct2);
		return capitalConstruct2.clone();
	}

	private void syncEconomicCharacteristics(List<EconomicCharacteristic> persistentList, List<EconomicCharacteristic> newList) {
		Map<Long, EconomicCharacteristic> persistentMap = new HashMap<>();
		for (EconomicCharacteristic characteristic : persistentList) {
			persistentMap.put(characteristic.getId(), characteristic);
		}
		Set<Long> newIds = new HashSet<>();
		for (EconomicCharacteristic characteristic : newList) {
			EconomicCharacteristic persistent;
			if (characteristic.getId() == null || characteristic.getId() == 0) {
				persistent = new EconomicCharacteristic();
				persistentList.add(persistent);
			} else {
				persistent = persistentMap.get(characteristic.getId());
				newIds.add(persistent.getId());
			}
			BeanUtils.copyProperties(characteristic, persistent, new String[]{"id", "priceIndicator", "okof"});
			persistent.setPriceIndicator(characteristic.getPriceIndicator() != null ? priceIndicatorBean.load(characteristic.getPriceIndicator().getId()) : null);
			persistent.setOkof(characteristic.getOkof() != null ? okofBean.load(characteristic.getOkof().getId()) : null);
			economicCharacteristicBean.save(persistent);
		}
		persistentMap.entrySet().stream().filter(entry -> !newIds.contains(entry.getKey())).forEach(entry -> {
			economicCharacteristicBean.remove(persistentMap.get(entry.getValue()));
			persistentMap.remove(entry.getKey());
		});
	}

	private void syncTechnicalCharacteristics(List<TechnicalCharacteristic> persistentList, List<TechnicalCharacteristic> newList) {
		Map<Long, TechnicalCharacteristic> persistentMap = new HashMap<>();
		for (TechnicalCharacteristic characteristic : persistentList) {
			persistentMap.put(characteristic.getId(), characteristic);
		}
		Set<Long> newIds = new HashSet<>();
		for (TechnicalCharacteristic characteristic : newList) {
			TechnicalCharacteristic persistent;
			if (characteristic.getId() == null || characteristic.getId() == 0) {
				persistent = new TechnicalCharacteristic();
				persistentList.add(persistent);
			} else {
				persistent = persistentMap.get(characteristic.getId());
				newIds.add(persistent.getId());
			}
			BeanUtils.copyProperties(characteristic, persistent, new String[]{"id", "constructType", "technicalIndicator", "unitOfMeasure"});
			persistent.setConstructType(characteristic.getConstructType() != null ? constructTypeBean.load(characteristic.getConstructType().getId()) : null);
			persistent.setTechnicalIndicator(characteristic.getTechnicalIndicator() != null ? technicalIndicatorBean.load(characteristic.getTechnicalIndicator().getId()) : null);
			persistent.setUnitOfMeasure(characteristic.getUnitOfMeasure() != null ? okeiBean.load(characteristic.getUnitOfMeasure().getId()) : null);
			technicalCharacteristicBean.save(persistent);
		}
		persistentMap.entrySet().stream().filter(entry -> !newIds.contains(entry.getKey())).forEach(entry -> {
			technicalCharacteristicBean.remove(persistentMap.get(entry.getValue()));
			persistentMap.remove(entry.getKey());
		});
	}

	private void syncConstructiveElements(List<ConstructiveElement> persistentList, List<ConstructiveElement> newList) {
		if (newList == null || newList.size() == 0) {
			persistentList.clear();
		} else {
			Map<Long, ConstructiveElement> persistentMap = new HashMap<>();
			for (ConstructiveElement element : persistentList) {
				persistentMap.put(element.getId(), element);
			}
			Set<Long> newIds = new HashSet<>();
			for (ConstructiveElement element : newList) {
				ConstructiveElement persistent;
				if (element.getId() == null || element.getId() == 0) {
					persistent = new ConstructiveElement();
					persistentList.add(persistent);
				} else {
					persistent = persistentMap.get(element.getId());
					newIds.add(persistent.getId());
				}
				BeanUtils.copyProperties(element, persistent, new String[]{"id", "type"});
				persistent.setType(element.getType() != null ? constructiveElementTypeBean.load(element.getType().getId()) : null);
				constructiveElementBean.save(persistent);
			}
			persistentMap.entrySet().stream().filter(entry -> !newIds.contains(entry.getKey())).forEach(entry -> {
				constructiveElementBean.remove(persistentMap.get(entry.getValue()));
				persistentMap.remove(entry.getKey());
			});
		}
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
