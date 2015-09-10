package ru.sovzond.mgis2.kladr;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;

/**
 * Created by Alexander Arakelyan on 10.09.15.
 */
@Repository
public class KLADRStreetDao extends CRUDDaoBase<KLADRStreet> {
	public PagerBuilderBase<KLADRStreet> createFilter(String name, String orderBy, int first, int max) {
		return new KLADRStreetFilter(name, orderBy, first, max);
	}

	private class KLADRStreetFilter extends PagerBuilderCriteria<KLADRStreet> {
		private final String name;

		public KLADRStreetFilter(String name, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.name = name;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (name != null && !"".equals(name)) {
				criteria.add(Restrictions.like("name", "%" + name + "%"));
			}
		}
	}
}
