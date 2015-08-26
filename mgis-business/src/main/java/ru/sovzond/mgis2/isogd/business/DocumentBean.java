package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.isogd.document.DocumentDao;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 */
@Service
public class DocumentBean {

	@Autowired
	private DocumentDao dao;

	public Document load(Long id) {
		return dao.findById(id);
	}

	public void save(Document document) {
		dao.save(document);
	}

	public void delete(Document document) {
		dao.delete(document);
	}

	public PageableContainer<Document> pageDocuments(Volume volume, String orderBy, int first, int max) {
		Pageable<Document> pager = dao.pager(dao.createFilter(volume, orderBy, first, max));
		List<Document> documents = pager.list().stream().map(document -> document.clone()).collect(Collectors.toList());
		return new PageableContainer<>(documents, pager.count(), first, max);
	}

	public List<DocumentSubObject> listDocumentSubObjectsByVolume(Volume volume) {
		return dao.listAvailableDocumentSubObjects(volume);
	}

	public DocumentClass readDocumentClassByVolume(Volume volume) {
		return dao.readDocumentClassByVolume(volume);
	}
}
