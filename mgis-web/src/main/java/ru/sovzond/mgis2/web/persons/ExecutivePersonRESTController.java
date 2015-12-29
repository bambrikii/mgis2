package ru.sovzond.mgis2.web.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.registers.persons.ExecutivePerson;
import ru.sovzond.mgis2.persons.ExecutivePersonBean;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@RestController
@RequestMapping("/persons/executive-persons")
@Scope("session")
public class ExecutivePersonRESTController implements Serializable {

	@Autowired
	private ExecutivePersonBean executivePersonBean;


	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<ExecutivePerson> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "surname") String orderBy, @RequestParam(defaultValue = "") String name) {
		return executivePersonBean.list(name, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public ExecutivePerson save(@PathVariable("id") Long id, @RequestBody ExecutivePerson executivePerson) {
		ExecutivePerson executivePerson1;
		if (id == 0) {
			executivePerson1 = new ExecutivePerson();
		} else {
			executivePerson1 = executivePersonBean.load(id);
		}
		executivePerson1.setFirstName(executivePerson.getFirstName());
		executivePerson1.setSurname(executivePerson.getSurname());
		executivePerson1.setPatronymic(executivePerson.getPatronymic());
		executivePerson1.setPosition(executivePerson.getPosition());
		executivePersonBean.save(executivePerson1);
		return executivePerson1.clone();
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public ExecutivePerson read(@PathVariable Long id) {
		return executivePersonBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		executivePersonBean.remove(executivePersonBean.load(id));
	}


}
