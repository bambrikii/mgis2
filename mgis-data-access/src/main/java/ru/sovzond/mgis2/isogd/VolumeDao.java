package ru.sovzond.mgis2.isogd;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.PageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableFilter;

@Repository
public class VolumeDao extends PageableDAOBase<Volume> {
	private static final String ID2 = "id";
	private static final String SECTION2 = "section";

	public Volume findById(Long id) {
		return (Volume) filter(Restrictions.eq(ID2, id)).uniqueResult();
	}

	public VolumeFilterBuilder createFilter(Section section) {
		return new VolumeFilterBuilder(section);
	}

	class VolumeFilterBuilder extends PageableFilter<Volume> {
		private VolumeFilterBuilder(Section section) {
			addCriterion(Restrictions.eq(SECTION2, section));
		}
	}
}
