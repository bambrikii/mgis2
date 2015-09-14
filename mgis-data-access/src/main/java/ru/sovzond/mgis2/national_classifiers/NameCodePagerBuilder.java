package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;

/**
 * Created by Alexander Arakelyan on 14.09.15.
 */
class NameCodePagerBuilder<T> extends PagerBuilderCriteria<T> {
	String name;

	NameCodePagerBuilder(String name, String orderBy, int first, int max) {
		super(orderBy, first, max);
		this.name = name;
	}

	@Override
	protected void applyFilter(Criteria criteria) {
		if (name != null && name.length() > 0) {
			criteria.add(Restrictions.or(Restrictions.like("code", name + "%"), Restrictions.like("name", "%" + name + "%")));
		}
	}
}
