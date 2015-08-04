package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.lands.TerritorialZoneTypeBean;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@RestController
@RequestMapping("/nc/territorial_zone_types")
@Scope("session")
public class TerritorialZoneTypeRESTController {
	@Autowired
	private TerritorialZoneTypeBean territorialZoneTypeBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<TerritorialZoneType> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return territorialZoneTypeBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public TerritorialZoneType read(@PathVariable Long id) {
		return territorialZoneTypeBean.load(id).clone();
	}
}
