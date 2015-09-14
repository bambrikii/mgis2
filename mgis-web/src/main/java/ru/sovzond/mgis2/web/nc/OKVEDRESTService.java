package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.OKVEDBean;
import ru.sovzond.mgis2.registers.national_classifiers.OKVED;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 14.09.15.
 */
@RestController
@RequestMapping("/nc/okved")
@Scope("session")
public class OKVEDRESTService {

	@Autowired
	private OKVEDBean okvedfBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<OKVED> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy, @RequestParam(defaultValue = "") String name) {
		return okvedfBean.list(orderBy, first, max, name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public OKVED read(@PathVariable Long id) {
		return okvedfBean.load(id).clone();
	}
}
