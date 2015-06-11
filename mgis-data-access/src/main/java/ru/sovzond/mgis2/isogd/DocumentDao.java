package ru.sovzond.mgis2.isogd;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.PageableDAOBase;

@Repository
public class DocumentDao extends PageableDAOBase<Document> {
	public Document findById(Long id) {
		return (Document) filter(Restrictions.eq("id", id)).uniqueResult();
	}
}
