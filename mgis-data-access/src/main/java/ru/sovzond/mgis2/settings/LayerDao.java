package ru.sovzond.mgis2.settings;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;

/**
 * Created by Alexander Arakelyan on 16.09.15.
 */
@Repository
public class LayerDao extends CRUDDaoBase<Layer> {
	public PagerBuilderBase<Layer> createFilter(String orderBy, int first, int max, String code) {
		return null;
	}

	private class LayerFilter extends PagerBuilderCriteria<Layer> {

		private String code;

		public LayerFilter(String orderBy, int first, int max, String code) {
			super(orderBy, first, max);
			this.code = code;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (code != null && code.length() > 0) {
				criteria.add(Restrictions.eq("code", code));
			}
		}
	}
}
