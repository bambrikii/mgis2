package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 26.08.15.
 */
public abstract class PagerBuilderQuery<T> extends PagerBuilderBase<T> {

	public static final String TABLE_ALIAS = "o";

	protected PagerBuilderQuery() {
		super((String) null, DEFAULT_LIMIT, DEFAULT_LIMIT);
	}

	protected PagerBuilderQuery(int first, int max) {
		super((String) null, first, max);
	}

	protected PagerBuilderQuery(String orderBy, int first, int max) {
		super(orderBy, first, max);
	}

	protected Pageable apply(Session session) {
		return new Pageable() {
			@Override
			public List<T> list() {
				StringBuilder queryBuilder = new StringBuilder().append("SELECT ").append(TABLE_ALIAS).append(" FROM ").append(persistentClass.getCanonicalName()).append(" ").append(TABLE_ALIAS);
				applyFilter(queryBuilder);
				applyOrder(queryBuilder);
				Query query = session.createQuery(queryBuilder.toString());
				applyLimit(query);
				applyParameters(query);
				return query.list();
			}

			@Override
			public Number count() {
				StringBuilder queryBuilder = new StringBuilder().append("SELECT count(").append(TABLE_ALIAS).append(") FROM ").append(persistentClass.getCanonicalName()).append(" ").append(TABLE_ALIAS);
				applyFilter(queryBuilder);
				Query query = session.createQuery(queryBuilder.toString());
				applyParameters(query);
				return (Number) query.uniqueResult();
			}
		};
	}

	protected abstract void applyFilter(StringBuilder queryBuilder);

	protected abstract void applyParameters(Query query);

	protected void addFilter(StringBuilder queryBuilder, String filter) {
		addFilter("WHERE", queryBuilder, filter);
	}

	protected void addAndFilter(StringBuilder queryBuilder, String filter) {
		addFilter("AND", queryBuilder, filter);
	}

	protected void addOrFilter(StringBuilder queryBuilder, String filter) {
		addFilter("OR", queryBuilder, filter);
	}

	private void addFilter(String type, StringBuilder queryBuilder, String filter) {
		queryBuilder.append(" ").append(type).append(" ").append(filter);
	}

	protected void applyOrder(StringBuilder queryBuilder) {
		if (orderBy != null && orderBy.length > 0) {
			queryBuilder.append(" ORDER BY ").append(orderBy[0]);
			for (int i = 1; i < orderBy.length; i++) {
				queryBuilder.append(", ").append(orderBy[i]);
			}
		}
	}

	protected void applyLimit(Query query) {
		if (max != DEFAULT_LIMIT) {
			query.setMaxResults(max);
			if (first != DEFAULT_LIMIT) {
				query.setFirstResult(first);
			}
		}
	}
}
