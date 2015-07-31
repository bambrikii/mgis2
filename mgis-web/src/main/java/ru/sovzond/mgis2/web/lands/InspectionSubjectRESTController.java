package ru.sovzond.mgis2.web.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.lands.LandControlInspectionSubjectBean;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.registers.lands.control.LandControlInspectionSubject;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@RestController
@RequestMapping("/lands/inspection_subjects")
@Scope("session")
public class InspectionSubjectRESTController implements Serializable {

	@Autowired
	private LandControlInspectionSubjectBean inspectionSubjectBean;


	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<LandControlInspectionSubject> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "name") String orderBy) {
		return inspectionSubjectBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public LandControlInspectionSubject read(@PathVariable Long id) {
		return inspectionSubjectBean.load(id).clone();
	}

}
