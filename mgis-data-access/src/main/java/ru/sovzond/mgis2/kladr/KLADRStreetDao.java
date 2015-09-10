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
	public PagerBuilderBase<KLADRStreet> createFilter(String subjectCode, String regionCode, String localityCode, String streetName, String orderBy, int first, int max) {
		return new KLADRStreetFilter(subjectCode, regionCode, localityCode, streetName, orderBy, first, max);
	}

	private class KLADRStreetFilter extends PagerBuilderCriteria<KLADRStreet> {
		private final String subjectCode;
		private final String regionCode;
		private final String localityCode;
		private final String streetName;

		public KLADRStreetFilter(String subjectCode, String regionCode, String localityCode, String streetName, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.subjectCode = subjectCode;
			this.regionCode = regionCode;
			this.localityCode = localityCode;
			this.streetName = streetName;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			new KLADRCriteriaBuilder()
					.subjectCode(subjectCode)
					.regionCode(regionCode)
					.localityCode(localityCode)
					.filterStreet(criteria, streetName);
			if (streetName != null && !"".equals(streetName)) {
				criteria.add(Restrictions.like("name", "%" + streetName + "%"));
			}
		}
	}
}
