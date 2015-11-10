package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.OKOFBean;
import ru.sovzond.mgis2.registers.national_classifiers.OKOF;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 14.09.15.
 */
@RestController
@RequestMapping("/nc/okof")
@Scope("session")
public class OKOFRESTService {

	@Autowired
	private OKOFBean okofBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<OKOF> list(@RequestParam(defaultValue = "") String code, @RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "name") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return okofBean.list(code, name, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public OKOF read(@PathVariable Long id) {
		return okofBean.load(id).clone();
	}
}
