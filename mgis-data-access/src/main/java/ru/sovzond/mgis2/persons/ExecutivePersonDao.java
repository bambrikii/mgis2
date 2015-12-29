package ru.sovzond.mgis2.persons;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.persons.ExecutivePerson;

import javax.naming.spi.ResolveResult;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Repository
public class ExecutivePersonDao extends CRUDDaoBase<ExecutivePerson> {
	public PagerBuilderBase<ExecutivePerson> createFilter(String name, String orderBy, int first, int max) {
		return new ExecutivePersonFilter(name, orderBy, first, max);
	}

	private class ExecutivePersonFilter extends PagerBuilderCriteria<ExecutivePerson> {
		private String name;

		private ExecutivePersonFilter(String name, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.name = name;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (name != null && name.length() > 0) {
				criteria.add(Restrictions.or(
						Restrictions.like("surname", "%" + name + "%"),
						Restrictions.like("firstName", "%" + name + "%"),
						Restrictions.like("patronymic", "%" + name + "%")
				));
			}
		}
	}
}
