package ru.sovzond.mgis2.address;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;

@Repository
public class AddressDao extends CRUDDaoBase<Address> {

	public PagerBuilderBase<Address> createFilter(String name, String orderBy, int first, int max) {
		return new AddressPagerBuilder(name, orderBy, first, max);
	}

	private class AddressPagerBuilder extends PagerBuilderCriteria<Address> {

		private String name;

		public AddressPagerBuilder(String name, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.name = name;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (name != null && name.length() > 0) {
				criteria.createAlias("subject", "subject");
				criteria.createAlias("region", "region");
				criteria.createAlias("locality", "locality");
				criteria.createAlias("street", "street");
				criteria.add(Restrictions.or( //
						Restrictions.like("subject.name", name + "%"), //
						Restrictions.like("region.name", name + "%"),//
						Restrictions.like("locality.name", name + "%"), //
						Restrictions.like("street.name", name + "%"), //
						Restrictions.like("home", name + "%") //
				));
			}
		}
	}
}
