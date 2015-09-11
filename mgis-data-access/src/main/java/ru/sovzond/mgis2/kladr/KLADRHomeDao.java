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
public class KLADRHomeDao extends CRUDDaoBase<KLADRHome> {
	public PagerBuilderBase<KLADRHome> createFilter(String streetCode, String homeName, String orderBy, int first, int max) {
		return new KLADRHomeFilter(streetCode, homeName, orderBy, first, max);
	}

	public PagerBuilderBase<KLADRHome> createHomeFilter(String streetCode, String homeName, String orderBy, int first, int max) {
		return new KLADRHomeFilter(streetCode, homeName, orderBy, first, max);
	}

	public PagerBuilderBase<KLADRHome> createHousingFilter(String streetCode, String homeName, String housingName, String orderBy, int first, int max) {
		return new KLADRHousingFilter(streetCode, homeName, housingName, orderBy, first, max);
	}

	private class KLADRHomeFilter extends PagerBuilderCriteria<KLADRHome> {
		private String streetCode;
		private String homeName;

		public KLADRHomeFilter(String streetCode, String homeName, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.homeName = homeName;
			this.streetCode = streetCode;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (streetCode != null && streetCode.length() > 0) {
				criteria.add(Restrictions.like("code", streetCode.substring(0, 15) + "%"));
			}
			if (homeName != null && !"".equals(homeName)) {
				criteria.add(Restrictions.like("name", "%" + homeName + "%"));
			}
		}
	}

	private class KLADRHousingFilter extends KLADRHomeFilter {
		private String housingName;

		public KLADRHousingFilter(String streetCode, String homeName, String housingName, String orderBy, int first, int max) {
			super(streetCode, homeName, orderBy, first, max);
			this.housingName = housingName;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			super.applyFilter(criteria);
			if (housingName != null && !"".equals(housingName)) {
				criteria.add(Restrictions.like("housing", "%" + housingName + "%"));
			}
		}
	}
}
