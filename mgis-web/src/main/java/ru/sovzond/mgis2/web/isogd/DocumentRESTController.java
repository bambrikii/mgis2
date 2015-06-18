package ru.sovzond.mgis2.web.isogd;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.Document;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.business.CloneManager;
import ru.sovzond.mgis2.isogd.business.ISOGDBean;

@RestController
@RequestMapping("/isogd/documents")
@Scope("session")
public class DocumentRESTController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -440806213097386154L;

	@Autowired
	private ISOGDBean isogdBean;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PageableContainer<Document> list(@RequestParam("volumeId") Long volumeId, @RequestParam(defaultValue = "0") int first,
			@RequestParam(defaultValue = "0") int max) {
		Volume volume = isogdBean.readVolume(volumeId);
		return isogdBean.pageDocuments(volume, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	@Transactional
	public Document save(@PathVariable("id") Long id, @RequestBody Document document) {
		Document document2;
		if (id == 0) {
			document2 = new Document();
			document2.setVolume(isogdBean.readVolume(document.getVolume().getId()));
		} else {
			document2 = isogdBean.readDocument(id);
		}
		document2.setName(document.getName());
		isogdBean.save(document2);
		return CloneManager.clone(document2);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Document read(@PathVariable("id") Long id) {
		return CloneManager.clone(isogdBean.readDocument(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Transactional
	public void delete(@PathVariable Long id) {
		isogdBean.delete(isogdBean.readDocument(id));
	}
}
