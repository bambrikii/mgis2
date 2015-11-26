package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.national_classifiers.LandCategory;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@Repository
public class LandCategoryDao extends CRUDDaoBase<LandCategory> {
	public PagerBuilderBase<LandCategory> createFilter(String code, String orderBy, int first, int max) {
		return new LandCategoryFilter(code, orderBy, first, max);
	}

	private class LandCategoryFilter extends PagerBuilderCriteria<LandCategory> {

		private String code;

		private LandCategoryFilter(String code, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.code = code;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (code != null) {
				criteria.add(Restrictions.like("code", "%" + code + "%"));
			}
		}
	}

	public LandCategory findByCode(String code) {
		return (LandCategory) getSession().createCriteria(persistentClass).add(Restrictions.eq("code", code)).uniqueResult();
	}
}
