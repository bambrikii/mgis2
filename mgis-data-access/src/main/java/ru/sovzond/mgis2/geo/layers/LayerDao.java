package ru.sovzond.mgis2.geo.layers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;

/**
 * Created by Alexander Arakelyan on 10/10/15.
 */
@Repository
public class LayerDao extends CRUDDaoBase<Layer> {
	public PagerBuilderBase<Layer> createPager(Layer parent, String orderBy, int first, int max) {
		return new LayerPager(parent, orderBy, first, max);
	}

	private class LayerPager extends PagerBuilderCriteria<Layer> {

		private Layer parent;

		private LayerPager(Layer parent, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.parent = parent;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(parent != null ? Restrictions.eq("parent", parent) : Restrictions.isNull("parent"));
		}
	}
}
