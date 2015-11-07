package ru.sovzond.mgis2.web.capital_constructs;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.capital_construct.CapitalConstructBean;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruct;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 */
@RestController
@RequestMapping("/capital-constructs/constructs")
@Scope("session")
public class CapitalConstructRESTService {
	@Autowired
	private CapitalConstructBean capitalConstructBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<CapitalConstruct> list(@RequestParam(value = "orderBy", defaultValue = "id DESC") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return capitalConstructBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public CapitalConstruct save(@PathVariable Long id, @RequestBody CapitalConstruct capitalConstruct) {
		CapitalConstruct capitalConstruct2;
		if (id == 0) {
			capitalConstruct2 = new CapitalConstruct();
		} else {
			capitalConstruct2 = capitalConstructBean.load(id);
		}
		BeanUtils.copyProperties(capitalConstruct, capitalConstruct2, new String[]{"id", "parent", "childLayers"});
		capitalConstructBean.save(capitalConstruct2);
		return capitalConstruct2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public CapitalConstruct read(@PathVariable Long id) {
		return capitalConstructBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		capitalConstructBean.remove(capitalConstructBean.load(id));
	}

}
