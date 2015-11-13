package ru.sovzond.mgis2.web.capital_constructs;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.capital_construct.ConstructiveElementTypeBean;
import ru.sovzond.mgis2.capital_constructs.constructive_elements.ConstructiveElementType;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 */
@RestController
@RequestMapping("/capital-constructs/constructive-element-types")
@Scope("session")
public class ConstructiveElementTypeRESTService {

	@Autowired
	private ConstructiveElementTypeBean constructiveElementTypeBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<ConstructiveElementType> list(@RequestParam(value = "orderBy", defaultValue = "id DESC") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return constructiveElementTypeBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public ConstructiveElementType save(@PathVariable Long id, @RequestBody ConstructiveElementType constructiveElementType) {
		ConstructiveElementType constructiveElementType2;
		if (id == 0) {
			constructiveElementType2 = new ConstructiveElementType();
		} else {
			constructiveElementType2 = constructiveElementTypeBean.load(id);
		}
		BeanUtils.copyProperties(constructiveElementType, constructiveElementType2, new String[]{"id"});
		constructiveElementTypeBean.save(constructiveElementType2);
		return constructiveElementType2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public ConstructiveElementType read(@PathVariable Long id) {
		return constructiveElementTypeBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		constructiveElementTypeBean.remove(constructiveElementTypeBean.load(id));
	}

}
