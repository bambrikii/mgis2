package ru.sovzond.mgis2.reports;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 11/12/15.
 */
@Repository
public class ReportDao extends CRUDDaoBase<Report> {
	public ReportFilter3 createFilter(String filter, String orderBy, int first, int max) {
		return new ReportFilter3(filter, orderBy, first, max);
	}

	private class ReportFilter3 extends PagerBuilderBase<Report> {
		private String filter;

		private ReportFilter3(String filter, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.filter = filter;
		}

		@Override
		protected Pageable apply(Session session) {
			StringBuilder queryBuilder = new StringBuilder(" FROM Report r LEFT JOIN r.filters filters");
			if (isFilterSet()) {
				queryBuilder.append(" WHERE filters = :q");
			}
			return new Pageable() {
				@Override
				public List list() {
					String queryString = "SELECT DISTINCT r " + queryBuilder.toString() + " ORDER BY r.sortOrder";
					Query query = session.createQuery(queryString);
					if (isFilterSet()) {
						query.setParameter("q", filter);
					}
					return query.list();
				}

				@Override
				public Number count() {
					String queryString = "SELECT count(r) " + queryBuilder.toString();
					Query query = session.createQuery(queryString);
					if (isFilterSet()) {
						query.setParameter("q", filter);
					}
					return (Number) query.uniqueResult();
				}
			};
		}

		private boolean isFilterSet() {
			return filter != null && filter.length() > 0;
		}
	}
}
