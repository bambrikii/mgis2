package ru.sovzond.mgis2.isogd;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.PageableDAOBase;

@Repository
public class VolumeDao extends PageableDAOBase<Volume> {
	public Volume findById(Long id) {
		return (Volume) filter(Restrictions.eq("id", id)).uniqueResult();
	}
}
