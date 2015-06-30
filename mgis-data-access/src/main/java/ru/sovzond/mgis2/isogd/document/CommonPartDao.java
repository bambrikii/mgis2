package ru.sovzond.mgis2.isogd.document;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableCRUDDaoBase;
import ru.sovzond.mgis2.isogd.document.parts.CommonPart;

import java.util.List;

/**
 * Created by asd on 22/06/15.
 */
@Repository
public class CommonPartDao extends PageableCRUDDaoBase<CommonPart> {
	public List<DocumentContent> listDocumentContents(Document document) {
		return getSession().createQuery("SELECT dcs FROM Document d JOIN d.commonPart cp JOIN cp.documentContents dcs WHERE d = :doc") //
				.setParameter("doc", document) //
				.list();
	}
}
