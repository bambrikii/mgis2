package ru.sovzond.mgis2.lands;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderQuery;
import ru.sovzond.mgis2.registers.lands.Land;

@Repository
public class LandDao extends CRUDDaoBase<Land> {
	public LandsFilter createFilter(String cadastralNumber, Long[] ids, String orderBy, int first, int max) {
		return new LandsFilter(cadastralNumber, ids, orderBy, first, max);
	}

	class LandsFilter extends PagerBuilderQuery<Land> {
		private String cadastralNumber;
		private Long[] ids;

		LandsFilter(String cadastralNumber, Long[] ids, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.cadastralNumber = cadastralNumber;
			this.ids = ids;
		}

		@Override
		protected void applyFilter(StringBuilder queryBuilder) {
			if (cadastralNumber != null && cadastralNumber.length() > 0) {
				addFilter(queryBuilder, "cadastralNumber LIKE :cadastralNumber");
			}
		}

		@Override
		protected void applyOrder(StringBuilder queryBuilder) {
			if (ids != null && ids.length > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append(ids[0]);
				for (int i = 1; i < ids.length; i++) {
					sb.append(",").append(ids[i]);
				}
				queryBuilder.append(" ORDER BY CASE WHEN id IN (").append(sb.toString()).append(") THEN 0 ELSE 1 END ");
			} else {
				super.applyOrder(queryBuilder);
			}
		}

		@Override
		protected void applyParameters(Query query) {
			if (cadastralNumber != null && cadastralNumber.length() > 0) {
				query.setParameter("cadastralNumber", "%" + cadastralNumber + "%");
			}
		}
	}
}
