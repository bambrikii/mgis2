package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

@Repository
public class OKTMODao extends CRUDDaoBase<OKTMO> {
	public PagerBuilderCriteria<OKTMO> createFilter(String name, String orderBy, int first, int max) {
		return new OKTMOFilter(name, orderBy, first, max);
	}

	private class OKTMOFilter extends PagerBuilderCriteria<OKTMO> {

		private String name;

		public OKTMOFilter(String name, String orderBy, int first, int max) {
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
