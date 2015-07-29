package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.OKATOBean;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@RestController
@RequestMapping("/nc/okato")
@Scope("session")
public class OKATORESTController {

	@Autowired
	private OKATOBean okatoBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<OKATO> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "code") String orderBy) {
		return okatoBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public OKATO read(@PathVariable Long id) {
		return okatoBean.load(id).clone();
	}

}
