package ru.sovzond.mgis2.isogd;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.impl.PageableBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableDAOBase;

@Repository
public class VolumeDao extends PageableDAOBase<Volume> {
	private static final String ID2 = "id";
	private static final String SECTION = "book";

	public Volume findById(Long id) {
		return (Volume) filter(Restrictions.eq(ID2, id)).uniqueResult();
	}

	public VolumeBaseBuilder createFilter(Book book, int first, int max) {
		return new VolumeBaseBuilder(book, first, max);
	}

	class VolumeBaseBuilder extends PageableBase<Volume> {
		private Book book;

		private VolumeBaseBuilder(Book book, int first, int max) {
			super(first, max);
			this.book = book;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(Restrictions.eq(SECTION, book));
		}
	}
}
