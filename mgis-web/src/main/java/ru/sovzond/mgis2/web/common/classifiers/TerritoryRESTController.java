package ru.sovzond.mgis2.web.common.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.common.classifiers.oktmo.Territory;
import ru.sovzond.mgis2.common.territory.TerritoryBean;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/common/classifiers/territory")
@Scope("session")
public class TerritoryRESTController implements Serializable {
	@Autowired
	private TerritoryBean territoryBean;


	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Territory> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		PageableContainer<Territory> pager = territoryBean.list(first, max);
		return new PageableContainer<>(pager.getList().stream().map(item -> item.clone()).collect(Collectors.toList()), pager.getTotalNumberOfItems(), first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@Transactional
	public Territory save(@PathVariable("id") Long id, @RequestBody Territory territory) {
		Territory result;
		if (id == 0) {
			result = new Territory();
		} else {
			result = territoryBean.load(id);
		}
		result.setName(territory.getName());
		result.setCode(territory.getCode());
		result.setComment(territory.getComment());
		result.setParent(territoryBean.load(territory.getParent().getId()));
		territoryBean.save(result);
		return result.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public Territory read(@PathVariable Long id) {
		return territoryBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		territoryBean.remove(territoryBean.load(id));
	}
}
