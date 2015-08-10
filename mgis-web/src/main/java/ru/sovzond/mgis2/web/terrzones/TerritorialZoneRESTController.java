package ru.sovzond.mgis2.web.terrzones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.lands.TerritorialZoneBean;
import ru.sovzond.mgis2.lands.TerritorialZoneTypeBean;
import ru.sovzond.mgis2.registers.lands.TerritorialZone;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@RestController
@RequestMapping("/terr-zones")
@Scope("session")
public class TerritorialZoneRESTController {
	@Autowired
	private TerritorialZoneBean territorialZoneBean;

	@Autowired
	private TerritorialZoneTypeBean territorialZoneTypeBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<TerritorialZone> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy, @RequestParam(defaultValue = "") String name) {
		return territorialZoneBean.list(orderBy, first, max, name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public TerritorialZone read(@PathVariable Long id) {
		return territorialZoneBean.load(id).clone();
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public TerritorialZone save(@PathVariable Long id, @RequestBody TerritorialZone zone) {
		TerritorialZone zone2;
		if (id == 0) {
			zone2 = new TerritorialZone();
		} else {
			zone2 = territorialZoneBean.load(id);
		}
		zone2.setZoneType(zone != null ? territorialZoneTypeBean.load(zone.getId()) : null);
		zone2.setName(zone.getName());
		return zone2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		territorialZoneBean.remove(territorialZoneBean.load(id));
	}

}
