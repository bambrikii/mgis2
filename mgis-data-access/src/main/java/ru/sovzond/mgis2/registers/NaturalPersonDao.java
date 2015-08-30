package ru.sovzond.mgis2.registers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.persons.NaturalPerson;

/**
 * Created by Alexander Arakelyan on 30/08/15.
 */
@Repository
public class NaturalPersonDao extends CRUDDaoBase<NaturalPerson> {
	public PagerBuilderBase<NaturalPerson> createFilter(String name, String orderBy, int first, int max) {
		return new NaturalPersonPagerBuilder(name, orderBy, first, max);
	}

	private class NaturalPersonPagerBuilder extends PagerBuilderCriteria<NaturalPerson> {

		private String name;

		private NaturalPersonPagerBuilder(String name, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.name = name;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (name != null && !"".equals(name)) {
				criteria.add(Restrictions.or(Restrictions.like("name", "%" + name + "%"), Restrictions.like("firstName", "%" + name + "%"), Restrictions.like("surname", "%" + name + "%"), Restrictions.like("patronymic", "%" + name + "%")));
			}
		}
	}
}
