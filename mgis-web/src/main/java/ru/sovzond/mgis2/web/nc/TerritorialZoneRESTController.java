package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.TerritorialZoneBean;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZone;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@RestController
@RequestMapping("/nc/territorial_zones")
@Scope("session")
public class TerritorialZoneRESTController {
	@Autowired
	private TerritorialZoneBean territorialZoneBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<TerritorialZone> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "code") String orderBy) {
		return territorialZoneBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public TerritorialZone read(@PathVariable Long id) {
		return territorialZoneBean.load(id).clone();
	}
}
