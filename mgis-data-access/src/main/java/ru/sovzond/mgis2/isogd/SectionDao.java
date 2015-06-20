package ru.sovzond.mgis2.isogd;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.impl.PageableDAOBase;

@Repository
public class SectionDao extends PageableDAOBase<Section> {
	public Section findById(Long id) {
		return (Section) filter(Restrictions.eq("id", id)).uniqueResult();
	}
}
