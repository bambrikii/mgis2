package ru.sovzond.mgis2.web.isogd;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.business.SectionBean;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentClassBean;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;

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
	private SectionBean sectionBean;

	@Autowired
	private DocumentClassBean documentClassBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Section> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "sortOrder") String orderBy, @RequestParam(defaultValue = "") String name) {
		return sectionBean.pageSections(orderBy, first, max, name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Section save(@PathVariable Long id, @RequestBody Section section) {
		Section section2 = (id == 0) ? new Section() : sectionBean.readSection(id);
		BeanUtils.copyProperties(section, section2, new String[]{"id", "documentClass", "books"});
		DocumentClass documentclass = section.getDocumentClass();
		if (documentclass != null && documentclass.getId() > 0) {
			section2.setDocumentClass(documentClassBean.load(documentclass.getId()));
		} else {
			section2.setDocumentClass(null);
		}
		sectionBean.save(section2);
		return section2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public Section read(@PathVariable Long id) {
		return sectionBean.readSection(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		sectionBean.delete(sectionBean.readSection(id));
	}

	@RequestMapping(value = "/swap-orders", method = RequestMethod.POST)
	@Transactional
	public void swapOrders(@RequestBody SwapIdPair pair) {
		SwapManager.byOrder(pair, sectionBean);
	}

}
