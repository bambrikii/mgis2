package ru.sovzond.mgis2.web.common.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.common.classifiers.ExecutivePerson;
import ru.sovzond.mgis2.common.classifiers.ExecutivePersonBean;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@RestController
@RequestMapping("/common/executive_persons")
@Scope("session")
public class ExecutivePersonRESTController implements Serializable {

	@Autowired
	private ExecutivePersonBean executivePersonBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<ExecutivePerson> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "fullName") String orderBy) {
		return executivePersonBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public ExecutivePerson read(@PathVariable Long id) {
		return executivePersonBean.load(id).clone();
	}

}
