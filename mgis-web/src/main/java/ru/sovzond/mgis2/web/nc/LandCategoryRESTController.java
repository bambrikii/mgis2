package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.LandCategoryBean;
import ru.sovzond.mgis2.registers.national_classifiers.LandCategory;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@RestController
@RequestMapping("/nc/land_categories")
@Scope("session")
public class LandCategoryRESTController {
	@Autowired
	private LandCategoryBean landCategoryBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LandCategory> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return landCategoryBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LandCategory read(@PathVariable Long id) {
		return landCategoryBean.load(id).clone();
	}
}
