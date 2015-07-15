package ru.sovzond.mgis2.isogd.business.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableBase;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObjectDao;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class DocumentObjectBean extends CRUDBeanBase<DocumentObject> {
	@Autowired
	private DocumentObjectDao dao;

	@Override
	protected IPageableDAOBase<DocumentObject> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<DocumentObject> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<DocumentObject> list(DocumentClass documentClass, int first, int max) {
		PageableBase<DocumentObject> pager = dao.createFilter(documentClass, first, max);
		return new PageableContainer<>(dao.list(pager), dao.count(pager), first, max);
	}
}
