package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableFilter;
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
	private DocumentDao documentDao;

	public Document readDocument(Long id) {
		return documentDao.findById(id);
	}

	public void save(Document document) {
		documentDao.save(document);
	}

	public void delete(Document document) {
		documentDao.delete(document);
	}

	public PageableContainer<Document> pageDocuments(Volume volume, int first, int max) {
		PageableFilter<Document> filter = documentDao.createFilter(volume);
		List<Document> documents = documentDao.list(first, max, filter).stream().map(document -> document.clone()).collect(Collectors.toList());
		return new PageableContainer<>(documents, documentDao.count(filter), first, max);
	}

	public List<DocumentSubObject> listDocumentSubObjectsByVolume(Volume volume) {
		return documentDao.listAvailableDocumentSubObjects(volume);
	}

	public DocumentClass readDocumentClassByVolume(Volume volume) {
		return documentDao.readDocumentClassByVolume(volume);
	}
}
