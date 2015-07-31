package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.LandEncumbranceBean;
import ru.sovzond.mgis2.registers.national_classifiers.LandEncumbrance;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@RestController
@RequestMapping("/nc/land_encumbrances")
@Scope("session")
public class LandEncumbranceRESTController {
	@Autowired
	private LandEncumbranceBean landEncumbranceBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LandEncumbrance> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return landEncumbranceBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LandEncumbrance read(@PathVariable Long id) {
		return landEncumbranceBean.load(id).clone();
	}
}
