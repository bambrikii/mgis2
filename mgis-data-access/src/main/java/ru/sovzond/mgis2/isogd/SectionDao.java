package ru.sovzond.mgis2.isogd;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;

@Repository
public class SectionDao extends CRUDDaoBase<Section> {
	public Section findById(Long id) {
		return (Section) filter(Restrictions.eq("id", id)).uniqueResult();
	}
}
