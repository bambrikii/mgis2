package ru.sovzond.mgis2.web.capital_constructs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sovzond.mgis2.capital_construct.ConstructTypeBean;
import ru.sovzond.mgis2.capital_constructs.ConstructType;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 11.11.15.
 */
@RestController
@RequestMapping("/capital-constructs/construct-types")
@Scope("session")
public class ConstructTypeRESTService {

	@Autowired
	private ConstructTypeBean constructTypeBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<ConstructType> list(@RequestParam(defaultValue = "name") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return constructTypeBean.list(orderBy, first, max);
	}
}
