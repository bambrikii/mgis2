package ru.sovzond.mgis2.web.isogd;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.business.ISOGDBean;

@RestController
@RequestMapping("/isogd/section")
@Scope("session")
public class SectionRESTController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4574289150137039686L;

	@Autowired
	private ISOGDBean isogdBean;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PageableContainer<Section> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return isogdBean.pageSections(first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	@Transactional
	public void save(@RequestParam Section section) {
		isogdBean.save(section);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Section read(@RequestParam Long id) {
		return isogdBean.readSection(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Transactional
	public void delete(@RequestParam Long id) {
		isogdBean.delete(isogdBean.readSection(id));
	}
}
