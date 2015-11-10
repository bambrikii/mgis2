package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.national_classifiers.OKEI;

/**
 * Created by Alexander Arakelyan on 10.11.15.
 */
@Repository
public class OKEIDao extends CRUDDaoBase<OKEI> {

	public PagerBuilderBase<OKEI> createFilter(String name, String orderBy, int first, int max) {
		return new OKEIFilter(name, orderBy, first, max);
	}

	private class OKEIFilter extends PagerBuilderCriteria<OKEI> {

		private String name;

		private OKEIFilter(String name, String orderBy, int first, int max) {
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
