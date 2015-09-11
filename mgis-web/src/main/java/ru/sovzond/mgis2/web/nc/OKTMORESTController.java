package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.OKTMOBean;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@RestController
@RequestMapping("/nc/oktmo")
@Scope("session")
public class OKTMORESTController {
	@Autowired
	private OKTMOBean oktmoBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<OKTMO> list(@RequestParam(defaultValue = "0") int first,
										 @RequestParam(defaultValue = "15") int max,
										 @RequestParam(defaultValue = "") String name,
										 @RequestParam(defaultValue = "") String code,
										 @RequestParam(defaultValue = "name") String orderBy
	) {
		return oktmoBean.list(code, name, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public OKTMO read(@PathVariable Long id) {
		return oktmoBean.load(id).clone();
	}
}
