package ru.sovzond.mgis2.web.isogd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.business.SectionBean;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentSubObjectBean;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.isogd.document.parts.CommonPart;
import ru.sovzond.mgis2.isogd.document.parts.SpecialPart;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/isogd/documents")
@Scope("session")
public class DocumentRESTController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -440806213097386154L;

	@Autowired
	private SectionBean isogdBean;

	@Autowired
	private DocumentSubObjectBean documentSubObjectBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Document> list(@RequestParam("volumeId") Long volumeId, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		Volume volume = isogdBean.readVolume(volumeId);
		return isogdBean.pageDocuments(volume, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
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
		document2.setDocNumber(document.getDocNumber());
		document2.setDocDate(document.getDocDate());
		document2.setDocumentSubObject(documentSubObjectBean.load(document.getDocumentSubObject().getId()));
		if (document.getCommonPart() != null) {
			if (document2.getCommonPart() == null) {
				CommonPart commonPart = new CommonPart();
				commonPart.setDocument(document2);
				document.setCommonPart(commonPart);
			}
		}
		if (document.getSpecialPart() != null) {
			if (document2.getSpecialPart() == null) {
				SpecialPart specialPart = new SpecialPart();
				specialPart.setDocument(document2);
				document.setSpecialPart(specialPart);
			}
		}
		isogdBean.save(document2);
		return document2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public Document read(@PathVariable("id") Long id) {
		return isogdBean.readDocument(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		isogdBean.delete(isogdBean.readDocument(id));
	}

	@RequestMapping(value = "/listDocumentSubObjectsByVolumeId/{volumeId}")
	@Transactional
	public PageableContainer<DocumentSubObject> listDocumentSubObjectList(@PathVariable Long volumeId) {
		return new PageableContainer<>(isogdBean.listDocumentSubObjectsByVolume(isogdBean.readVolume(volumeId)).stream().map(documentSubObject -> documentSubObject.clone()).collect(Collectors.toList()));
	}

	@RequestMapping(value = "/readDocumentClassByVolumeId/{volumeId}")
	@Transactional
	public DocumentClass readDocumentClassByVolumeId(@PathVariable Long volumeId) {
		return isogdBean.readDocumentClassByVolume(isogdBean.readVolume(volumeId)).clone();
	}
}
