package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.LandRightKindBean;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 */
@RestController
@RequestMapping("/nc/land_right_kinds")
@Scope("session")
public class LandRightKindRESTController {
	@Autowired
	private LandRightKindBean landRightKindBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LandRightKind> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return landRightKindBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LandRightKind read(@PathVariable Long id) {
		return landRightKindBean.load(id).clone();
	}

}
