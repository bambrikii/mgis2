package ru.sovzond.mgis2.web.nc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.national_classifiers.OKFSBean;
import ru.sovzond.mgis2.registers.national_classifiers.OKFS;

import javax.transaction.Transactional;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 */
@RestController
@RequestMapping("/nc/okfs")
@Scope("session")
public class OKFSRESTController {

	@Autowired
	private OKFSBean landOwnershipFormBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<OKFS> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return landOwnershipFormBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public OKFS read(@PathVariable Long id) {
		return landOwnershipFormBean.load(id).clone();
	}

}
