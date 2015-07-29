package ru.sovzond.mgis2.lands;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableBase;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.registers.lands.Land;

@Repository
public class LandDao extends CRUDDaoBase<Land> {
	public LandFilter createFilter(String cadastralNumber, String orderBy, int first, int max) {
		LandFilter filter = new LandFilter(cadastralNumber, orderBy, first, max);
		return filter;
	}

	class LandFilter extends PageableBase<Land> {
		private String cadastralNumber;

		LandFilter(String cadastralNumber, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.cadastralNumber = cadastralNumber;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (cadastralNumber != null && cadastralNumber.length() > 0) {
				criteria.add(Restrictions.like("cadastralNumber", cadastralNumber));
			}
		}

	}
}
