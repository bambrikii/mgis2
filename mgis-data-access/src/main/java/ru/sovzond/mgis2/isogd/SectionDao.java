package ru.sovzond.mgis2.isogd;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;

@Repository
public class SectionDao extends CRUDDaoBase<Section> {
	public Section findById(Long id) {
		return (Section) filter(Restrictions.eq("id", id)).uniqueResult();
	}

	public PagerBuilderBase<Section> createFilter(String name, String orderBy, int first, int max) {
		return new SectionFilter(name, orderBy, first, max);
	}

	private class SectionFilter extends PagerBuilderCriteria<Section> {

		private String name;

		private SectionFilter(String name, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.name = name;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (name != null && name.length() > 0) {
				criteria.add(Restrictions.like("name", "%" + name + "%"));
			}
		}
	}
}
