package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.OKEIBean;
import ru.sovzond.mgis2.registers.national_classifiers.OKEI;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 14.09.15.
 */
@RestController
@RequestMapping("/nc/okei")
@Scope("session")
public class OKEIRESTService {

	@Autowired
	private OKEIBean okeiBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<OKEI> list(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "name") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return okeiBean.list(name, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public OKEI read(@PathVariable Long id) {
		return okeiBean.load(id).clone();
	}
}
