package ru.sovzond.mgis2.isogd.business.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableBase;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObjectDao;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class DocumentSubObjectBean extends CRUDBeanBase<DocumentSubObject> {

	@Autowired
	private DocumentSubObjectDao dao;

	@Override
	protected IPageableDAOBase<DocumentSubObject> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<DocumentSubObject> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<DocumentSubObject> list(DocumentObject documentObject, int first, int max) {
		PageableBase<DocumentSubObject> pager = dao.createFilter(documentObject, first, max);
		return new PageableContainer<>(dao.list(pager), dao.count(pager), first, max);
	}

}
