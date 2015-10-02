package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.isogd.document.DocumentDao;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 */
@Service
public class DocumentBean extends CRUDBeanBase<Document> {

	@Autowired
	private DocumentDao dao;

	@Override
	protected IPageableDAOBase<Document> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Document> getIIdentifiableDao() {
		return dao;
	}

	public Document load(Long id) {
		return dao.findById(id);
	}

	public Document save(Document document) {
		dao.save(document);
		return document;
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

	public PageableContainer<Document> find(Section section, String documentName, Date documentDate, Date documentDateFrom, Date documentDateTill, String documentNumber, String orderBy, int first, int max) {
		PagerBuilderCriteria<Document> filter = dao.createSearchDocumentFilter(section, documentName, documentDate, documentDateFrom, documentDateTill, documentNumber, orderBy, first, max);
		Pageable<Document> pager = dao.pager(filter);
		return new PageableContainer<>(pager.list().stream().map(Document::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
