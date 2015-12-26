package ru.sovzond.mgis2.capital_construct;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 */
@Repository
public class CapitalConstructDao extends CRUDDaoBase<CapitalConstruction> {
	public CapitalConstructFilter createFilter(String cadastralNumber, String name, String orderBy, int first, int max) {
		return new CapitalConstructFilter(cadastralNumber, name, orderBy, first, max);
	}

	public CapitalConstruction findByCadastralNumber(String cadastralNumber) {
		return (CapitalConstruction) createCriteria().add(Restrictions.eq("cadastralNumber", cadastralNumber)).uniqueResult();
	}

	private class CapitalConstructFilter extends PagerBuilderCriteria<CapitalConstruction> {

		private final String cadastralNumber;
		private final String name;

		public CapitalConstructFilter(String cadastralNumber, String name, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.cadastralNumber = cadastralNumber;
			this.name = name;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (cadastralNumber != null && cadastralNumber.length() > 0) {
				criteria.add(Restrictions.like("cadastralNumber", "%" + cadastralNumber + "%"));
			}
			if (name != null && name.length() > 0) {
				criteria.add(Restrictions.like("name", "%" + name + "%"));
			}
		}
	}
}
