package ru.sovzond.mgis2.web.oks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.oks.NaturalPersonBean;
import ru.sovzond.mgis2.registers.persons.NaturalPerson;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 30/08/15.
 */
@RestController
@RequestMapping("/oks/natural-persons")
@Scope("session")
public class NaturalPersonRESTService implements Serializable {
	@Autowired
	private NaturalPersonBean naturalPersonBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<NaturalPerson> list(@RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "orderBy", defaultValue = "name") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return naturalPersonBean.list(name, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public NaturalPerson save(@PathVariable("id") Long id, @RequestBody NaturalPerson naturalPerson) {
		NaturalPerson naturalPerson1;
		if (id == 0) {
			naturalPerson1 = new NaturalPerson();
		} else {
			naturalPerson1 = naturalPersonBean.load(id);
		}
		naturalPerson1.setName(naturalPerson.getName());
		naturalPerson1.setFirstName(naturalPerson.getFirstName());
		naturalPerson1.setSurname(naturalPerson.getSurname());
		naturalPerson1.setPatronymic(naturalPerson.getPatronymic());
		return naturalPersonBean.save(naturalPerson1).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public NaturalPerson read(@PathVariable("id") Long id) {
		return naturalPersonBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		naturalPersonBean.remove(naturalPersonBean.load(id));
	}

}
