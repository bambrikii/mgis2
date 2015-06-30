package ru.sovzond.mgis2.isogd.document;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableCRUDDaoBase;
import ru.sovzond.mgis2.isogd.document.parts.SpecialPart;

import java.util.List;

/**
 * Created by asd on 22/06/15.
 */
@Repository
public class SpecialPartDao extends PageableCRUDDaoBase<SpecialPart> {
	public List<DocumentContent> listDocumentContents(Document document) {
		return getSession().createQuery("SELECT dcs FROM Document d JOIN d.specialPart sp JOIN sp.documentContents dcs WHERE d = :doc") //
				.setParameter("doc", document) //
				.list();
	}
}
