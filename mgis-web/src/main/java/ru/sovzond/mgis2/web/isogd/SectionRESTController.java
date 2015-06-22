package ru.sovzond.mgis2.web.isogd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.business.SectionBean;

import javax.transaction.Transactional;
import java.io.Serializable;

@RestController
@RequestMapping("/isogd/sections")
@Scope("session")
public class SectionRESTController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4574289150137039686L;

	@Autowired
	private SectionBean isogdBean;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PageableContainer<Section> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return isogdBean.pageSections(first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Section save(@PathVariable("id") Long id, @RequestBody Section section) {
		Section section2 = (id == 0) ? new Section() : isogdBean.readSection(id);
		section2.setName(section.getName());
		isogdBean.save(section2);
		return section2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Section read(@PathVariable Long id) {
		return isogdBean.readSection(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Transactional
	public void delete(@PathVariable Long id) {
		isogdBean.delete(isogdBean.readSection(id));
	}
}
