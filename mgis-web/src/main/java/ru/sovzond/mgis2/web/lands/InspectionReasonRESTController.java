package ru.sovzond.mgis2.web.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.common.classifiers.ExecutivePerson;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.lands.LandControlInspectionReasonBean;
import ru.sovzond.mgis2.registers.lands.control.LandControlInspectionReason;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@RestController
@RequestMapping("/lands/inspection_reasons")
@Scope("session")
public class InspectionReasonRESTController implements Serializable {

	@Autowired
	private LandControlInspectionReasonBean landControlInspectionReasonBean;


	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LandControlInspectionReason> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return landControlInspectionReasonBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LandControlInspectionReason read(@PathVariable Long id) {
		return landControlInspectionReasonBean.load(id).clone();
	}

}
