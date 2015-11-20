package ru.sovzond.mgis2.address;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;

import java.util.List;

@Repository
public class AddressDao extends CRUDDaoBase<Address> {

	public PagerBuilderBase<Address> createFilter(String name, String orderBy, int first, int max) {
		return new AddressPagerBuilder(name, orderBy, first, max);
	}

	public List<Address> find(AddressFilter filter) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria
				.createAlias("subject", "subject")
				.createAlias("region", "region")
				.createAlias("locality", "locality")
				.createAlias("street", "street")
		;
		if (filter.subject()) {
			criteria.add(Restrictions.eq("subject.name", filter.getSubject()));
			criteria.add(Restrictions.eq("subject.socr", filter.getSubjectType()));
		}
		if (filter.region()) {
			criteria.add(Restrictions.eq("region.name", filter.getRegion()));
			criteria.add(Restrictions.eq("region.socr", filter.getRegionType()));
		}
		if (filter.locality()) {
			criteria.add(Restrictions.eq("locality.name", filter.getLocality()));
			criteria.add(Restrictions.eq("locality.socr", filter.getLocalityType()));
		}
		criteria.add(filter.home() ? Restrictions.eq("home", filter.getHome()) : Restrictions.isEmpty("home"));
		criteria.add(filter.housing() ? Restrictions.eq("housing", filter.getHousing()) : Restrictions.isEmpty("housing"));
		criteria.add(filter.building() ? Restrictions.eq("building", filter.getBuilding()) : Restrictions.isEmpty("building"));
		criteria.add(filter.apartment() ? Restrictions.eq("apartment", filter.getApartment()) : Restrictions.isEmpty("apartment"));
		return criteria.list();
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
