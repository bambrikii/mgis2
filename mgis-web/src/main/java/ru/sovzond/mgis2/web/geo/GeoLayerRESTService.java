package ru.sovzond.mgis2.web.geo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.geo.layers.Layer;
import ru.sovzond.mgis2.geo.layers.LayerBean;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 10/10/15.
 */
@RestController
@RequestMapping("/geo/layers")
@Scope("session")
public class GeoLayerRESTService implements Serializable {

	@Autowired
	private LayerBean layerBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Layer> list(@RequestParam(value = "orderBy", defaultValue = "sortOrder") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "") String code, @RequestParam(defaultValue = "0") Long parentId) {
		Layer parent = (parentId != null && parentId != 0) ? layerBean.load(parentId) : null;
		return layerBean.list(parent, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Layer save(@PathVariable Long id, @RequestBody Layer layer) {
		Layer layer2;
		if (id == 0) {
			layer2 = new Layer();
		} else {
			layer2 = layerBean.load(id);
		}
		BeanUtils.copyProperties(layer, layer2, new String[]{"id", "parent", "childLayers"});
		layer2.setParent(layer.getParent() != null && layer.getParent().getId() != null && layer.getParent().getId() != 0 ? layerBean.load(layer.getParent().getId()) : null);
		layer2.setParams(layer.getParams().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
		layer2.setParent((layer.getParent() != null) ? layerBean.load(layer.getParent().getId()) : null);
		layerBean.save(layer2);
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
