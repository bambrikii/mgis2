package ru.sovzond.mgis2.web.settings;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.settings.Layer;
import ru.sovzond.mgis2.settings.LayerBean;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 16.09.15.
 */
@RestController
@RequestMapping("/settings/layers")
@Scope("session")
public class LayerRESTService {

	@Autowired
	private LayerBean layerBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Layer> list(@RequestParam(value = "name", defaultValue = "") String code, @RequestParam(value = "orderBy", defaultValue = "") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return layerBean.list(code, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Layer save(@PathVariable Long id, @RequestBody Layer book) {
		Layer layer2;
		if (id == 0) {
			layer2 = new Layer();
		} else {
			layer2 = layerBean.load(id);
		}
		BeanUtils.copyProperties(book, layer2, new String[]{"id"});
		return layer2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Layer read(@PathVariable Long id) {
		return layerBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		layerBean.remove(layerBean.load(id));
	}

}
