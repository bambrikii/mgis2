package ru.sovzond.mgis2.web.persons;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.*;
import ru.sovzond.mgis2.persons.LegalPersonBean;
import ru.sovzond.mgis2.persons.PersonBean;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;
import ru.sovzond.mgis2.registers.national_classifiers.OKOGU;
import ru.sovzond.mgis2.registers.national_classifiers.OKOPF;
import ru.sovzond.mgis2.registers.national_classifiers.OKVED;
import ru.sovzond.mgis2.registers.persons.LegalPerson;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 30/08/15.
 */
@RestController
@RequestMapping("/persons/legal-persons")
@Scope("session")
public class LegalPersonRESTService implements Serializable {
	@Autowired
	private LegalPersonBean legalPersonBean;

	@Autowired
	private OKFSBean ownershipFormBean;

	@Autowired
	private OKVEDBean okvedBean;

	@Autowired
	private OKOGUBean okoguBean;

	@Autowired
	private OKOPFBean okopfBean;

	@Autowired
	private OKATOBean okatoBean;

	@Autowired
	private AddressBean addressBean;

	@Autowired
	private PersonBean personBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LegalPerson> list(@RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "orderBy", defaultValue = "") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return legalPersonBean.list(name, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public LegalPerson save(@PathVariable("id") Long id, @RequestBody LegalPerson legalPerson) {
		LegalPerson legalPerson1;
		if (id == 0) {
			legalPerson1 = new LegalPerson();
		} else {
			legalPerson1 = legalPersonBean.load(id);
		}
		BeanUtils.copyProperties(legalPerson, legalPerson1, new String[]{"id", "ownershipForm", "founders", "activityType", "okogu", "organizationalForm", "actualAddressTerritoryOkatoCode", "actualAddress", "legalAddressTerritoryOkatoCode", "legalAddress",});
		legalPerson1.setOwnershipForm(legalPerson.getOwnershipForm() != null ? ownershipFormBean.load(legalPerson.getOwnershipForm().getId()) : null);
		if (legalPerson.getFounders() != null && legalPerson.getFounders().size() > 0) {
			legalPerson1.getFounders().clear();
			List<Long> founderIds = legalPerson.getFounders().stream().map(person -> person.getId()).collect(Collectors.toList());
			legalPerson1.getFounders().addAll(personBean.load(founderIds));
		}

		OKVED activityType = legalPerson.getActivityType();
		legalPerson1.setActivityType(activityType != null && activityType.getId() != null ? okvedBean.load(activityType.getId()) : null);

		OKOGU okogu = legalPerson.getOkogu();
		legalPerson1.setOkogu(okogu != null && okogu.getId() != null && okogu.getId() != 0 ? okoguBean.load(okogu.getId()) : null);

		OKOPF okopf = legalPerson.getOrganizationalForm();
		legalPerson1.setOrganizationalForm(okopf != null && okopf.getId() != null && okopf.getId() != 0 ? okopfBean.load(okopf.getId()) : null);

		// Actual Address
		OKATO actualAddressCode = legalPerson.getActualAddressTerritoryOkatoCode();
		legalPerson1.setActualAddressTerritoryOkatoCode(actualAddressCode != null && actualAddressCode.getId() != null && actualAddressCode.getId() != 0 ? okatoBean.load(actualAddressCode.getId()) : null);

		Address actualAddress = legalPerson.getActualAddress();
		legalPerson1.setActualAddress(actualAddress != null && actualAddress.getId() != null && actualAddress.getId() != 0 ? addressBean.load(actualAddress.getId()) : null);

		// Legal Address
		OKATO legalAddressCode = legalPerson.getLegalAddressTerritoryOkatoCode();
		legalPerson1.setLegalAddressTerritoryOkatoCode(legalAddressCode != null && legalAddressCode.getId() != null && legalAddressCode.getId() != 0 ? okatoBean.load(legalAddressCode.getId()) : null);

		Address legalAddress = legalPerson.getLegalAddress();
		legalPerson1.setLegalAddress(legalAddress != null && legalAddress.getId() != null && legalAddress.getId() != 0 ? addressBean.load(legalAddress.getId()) : null);


		return legalPersonBean.save(legalPerson1).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LegalPerson read(@PathVariable("id") Long id) {
		return legalPersonBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		legalPersonBean.remove(legalPersonBean.load(id));
	}
}
