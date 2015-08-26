package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Criteria;

/**
 * Created by Alexander Arakelyan on 15.07.15.
 */
public class PagerFactory {
	public static <T> PagerBuilderCriteria<T> createDefault(Class<T> cls, String orderBy, int first, int max) {
		return new DefaultPager<>(cls, orderBy, first, max);
	}

	public static <T> PagerBuilderCriteria<T> createDefault(Class<T> cls, int first, int max) {
		return new DefaultPager<>(cls, first, max);
	}

	private static class DefaultPager<T> extends PagerBuilderCriteria<T> {

		private DefaultPager(Class<T> cls, int first, int max) {
			super(cls, first, max);
		}

		private DefaultPager(Class<T> cls, String orderBy, int first, int max) {
			super(cls, orderBy, first, max);
		}

		@Override
		protected void applyFilter(Criteria criteria) {

		}
	}
}
