package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.LandAllowedUsageBean;
import ru.sovzond.mgis2.registers.national_classifiers.LandAllowedUsage;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@RestController
@RequestMapping("/nc/lands_allowed_usage")
@Scope("session")
public class LandAllowedUsageController {
	@Autowired
	private LandAllowedUsageBean landAllowedUsageBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LandAllowedUsage> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return landAllowedUsageBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LandAllowedUsage read(@PathVariable Long id) {
		return landAllowedUsageBean.load(id).clone();
	}
}
