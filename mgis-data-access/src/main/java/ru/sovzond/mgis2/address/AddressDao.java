package ru.sovzond.mgis2.address;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.kladr.KLADRLocality;
import ru.sovzond.mgis2.kladr.KLADRStreet;

import java.util.List;

@Repository
public class AddressDao extends CRUDDaoBase<Address> {

	public PagerBuilderBase<Address> createFilter(String name, String orderBy, int first, int max) {
		return new AddressPagerBuilder(name, orderBy, first, max);
	}

	public List<Address> find(KLADRLocality subject, KLADRLocality region, KLADRLocality locality, KLADRStreet street, String home, String housing, String building, String apartment) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.add(subject != null ? Restrictions.eq("subject", subject) : Restrictions.isEmpty("subject"));
		criteria.add(region != null ? Restrictions.eq("region", region) : Restrictions.isEmpty("region"));
		criteria.add(locality != null ? Restrictions.eq("locality", locality) : Restrictions.isEmpty("locality"));
		criteria.add(street != null ? Restrictions.eq("street", street) : Restrictions.isEmpty("street"));
		criteria.add(home != null ? Restrictions.eq("home", home) : Restrictions.isEmpty("home"));
		criteria.add(housing != null ? Restrictions.eq("housing", housing) : Restrictions.isEmpty("housing"));
		criteria.add(building != null ? Restrictions.eq("building", building) : Restrictions.isEmpty("building"));
		criteria.add(apartment != null ? Restrictions.eq("apartment", apartment) : Restrictions.isEmpty("apartment"));
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
