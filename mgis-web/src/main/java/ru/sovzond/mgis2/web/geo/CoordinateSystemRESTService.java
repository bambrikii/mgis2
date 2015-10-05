package ru.sovzond.mgis2.web.geo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.geo.CoordinateSystem;
import ru.sovzond.mgis2.geo.CoordinateSystemBean;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 05.10.15.
 */
@RestController
@RequestMapping("/geo/coordinate-systems")
@Scope("session")
public class CoordinateSystemRESTService {

	@Autowired
	private CoordinateSystemBean coordinateSystemBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<CoordinateSystem> list(@RequestParam(defaultValue = "") String code, @RequestParam(value = "orderBy", defaultValue = "code") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return coordinateSystemBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public CoordinateSystem save(@PathVariable Long id, @RequestBody CoordinateSystem coordinateSystem) {
		CoordinateSystem coordinateSystem2;
		if (id == 0) {
			coordinateSystem2 = new CoordinateSystem();
		} else {
			coordinateSystem2 = coordinateSystemBean.load(id);
		}
		BeanUtils.copyProperties(coordinateSystem, coordinateSystem2, new String[]{"id"});
		coordinateSystemBean.save(coordinateSystem2);
		return coordinateSystem2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public CoordinateSystem read(@PathVariable Long id) {
		return coordinateSystemBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		coordinateSystemBean.remove(coordinateSystemBean.load(id));
	}

}
