package ru.sovzond.mgis2.web.geo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.settings.GisServer;
import ru.sovzond.mgis2.settings.GisServerBean;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 16.09.15.
 */
@RestController
@RequestMapping("/geo/geo-servers")
@Scope("session")
public class GeoServerRESTService {

	@Autowired
	private GisServerBean layerBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<GisServer> list(@RequestParam(defaultValue = "") String code, @RequestParam(value = "orderBy", defaultValue = "code") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return layerBean.list(code, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public GisServer save(@PathVariable Long id, @RequestBody GisServer gisServer) {
		GisServer gisServer2;
		if (id == 0) {
			gisServer2 = new GisServer();
		} else {
			gisServer2 = layerBean.load(id);
		}
		BeanUtils.copyProperties(gisServer, gisServer2, new String[]{"id"});
		layerBean.save(gisServer2);
		return gisServer2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public GisServer read(@PathVariable Long id) {
		return layerBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		layerBean.remove(layerBean.load(id));
	}

}
