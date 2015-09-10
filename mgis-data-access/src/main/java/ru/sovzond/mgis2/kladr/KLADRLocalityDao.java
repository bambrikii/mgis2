package ru.sovzond.mgis2.kladr;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;

/**
 * Created by Alexander Arakelyan on 10.09.15.
 */
@Repository
public class KLADRLocalityDao extends CRUDDaoBase<KLADRLocality> {
	public PagerBuilderBase<KLADRLocality> createSubjectFilter(String subjectName, String orderBy, int first, int max) {
		return new KLADRSubjectFilter(subjectName, orderBy, first, max);
	}

	public PagerBuilderBase<KLADRLocality> createRegionFilter(String subjectCode, String regionName, String orderBy, int first, int max) {
		return new KLADRRegionFilter(subjectCode, regionName, orderBy, first, max);
	}

	public PagerBuilderBase<KLADRLocality> createLocalityFilter(String subjectCode, String regionCode, String localityName, String orderBy, int first, int max) {
		return new KLADRLocalityFilter(subjectCode, regionCode, localityName, orderBy, first, max);
	}

	private class KLADRSubjectFilter extends PagerBuilderCriteria<KLADRLocality> {

		private String subjectName;

		public KLADRSubjectFilter(String subjectName, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.subjectName = subjectName;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			new KLADRCriteriaBuilder().filterSubject(criteria, subjectName);
		}
	}

	private class KLADRRegionFilter extends PagerBuilderCriteria<KLADRLocality> {

		private String subjectCode;
		private String regionName;

		public KLADRRegionFilter(String subjectCode, String regionName, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.subjectCode = subjectCode;
			this.regionName = regionName;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			new KLADRCriteriaBuilder().subjectCode(subjectCode).filterRegion(criteria, regionName);
		}
	}

	private class KLADRLocalityFilter extends PagerBuilderCriteria<KLADRLocality> {
		private final String subjectCode;
		private final String regionCode;
		private final String localityName;

		public KLADRLocalityFilter(String subjectCode, String regionCode, String localityName, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.subjectCode = subjectCode;
			this.regionCode = regionCode;
			this.localityName = localityName;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			new KLADRCriteriaBuilder().subjectCode(subjectCode).regionCode(regionCode).filterLocality(criteria, localityName);
		}
	}
}
