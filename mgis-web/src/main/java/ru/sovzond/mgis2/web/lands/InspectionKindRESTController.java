package ru.sovzond.mgis2.web.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.lands.LandControlInspectionKindBean;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.registers.lands.control.LandControlInspectionKind;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@RestController
@RequestMapping("/lands/inspection_kinds")
@Scope("session")
public class InspectionKindRESTController implements Serializable {

	@Autowired
	private LandControlInspectionKindBean inspectionKindBean;


	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LandControlInspectionKind> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return inspectionKindBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LandControlInspectionKind read(@PathVariable Long id) {
		return inspectionKindBean.load(id).clone();
	}

}
