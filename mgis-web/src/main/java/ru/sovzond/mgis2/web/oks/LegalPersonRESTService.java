package ru.sovzond.mgis2.web.oks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.oks.LegalPersonBean;
import ru.sovzond.mgis2.registers.persons.LegalPerson;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 30/08/15.
 */
@RestController
@RequestMapping("/oks/legal-persons")
@Scope("session")
public class LegalPersonRESTService implements Serializable {
	@Autowired
	private LegalPersonBean legalPersonBean;

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
		legalPerson1.setName(legalPerson.getName());
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
