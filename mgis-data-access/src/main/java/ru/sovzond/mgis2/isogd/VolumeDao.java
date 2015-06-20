package ru.sovzond.mgis2.isogd;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.impl.PageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableFilter;

@Repository
public class VolumeDao extends PageableDAOBase<Volume> {
	private static final String ID2 = "id";
	private static final String SECTION2 = "book";

	public Volume findById(Long id) {
		return (Volume) filter(Restrictions.eq(ID2, id)).uniqueResult();
	}

	public VolumeFilterBuilder createFilter(Book book) {
		return new VolumeFilterBuilder(book);
	}

	class VolumeFilterBuilder extends PageableFilter<Volume> {
		private VolumeFilterBuilder(Book book) {
			addCriterion(Restrictions.eq(SECTION2, book));
		}
	}
}
