package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.LandOwnershipFormBean;
import ru.sovzond.mgis2.registers.national_classifiers.LandOwnershipForm;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 */
@RestController
@RequestMapping("/nc/land_ownership_forms")
@Scope("session")
public class LandOwnershipFormRESTController {

	@Autowired
	private LandOwnershipFormBean landOwnershipFormBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LandOwnershipForm> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return landOwnershipFormBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LandOwnershipForm read(@PathVariable Long id) {
		return landOwnershipFormBean.load(id).clone();
	}

}
