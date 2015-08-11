package ru.sovzond.mgis2.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.registers.LegalPersonBean;
import ru.sovzond.mgis2.registers.persons.LegalPerson;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 11.08.15.
 */
@RestController
@RequestMapping("/registers/legal_persons")
@Scope("session")
public class LegalPersonRESTController {
	@Autowired
	private LegalPersonBean legalPersonBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LegalPerson> list(@RequestParam(defaultValue = "0") int first,
											   @RequestParam(defaultValue = "0") int max,
											   @RequestParam(defaultValue = "name") String name,
											   @RequestParam(defaultValue = "name") String orderBy) {
		return legalPersonBean.list(name, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LegalPerson read(@PathVariable Long id) {
		return legalPersonBean.load(id).clone();
	}
}
