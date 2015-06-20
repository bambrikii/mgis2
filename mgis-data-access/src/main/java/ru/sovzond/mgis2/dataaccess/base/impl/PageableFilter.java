package ru.sovzond.mgis2.dataaccess.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;

public abstract class PageableFilter<T> {
	private List<Criterion> criteria = new ArrayList<Criterion>();

	protected PageableFilter<T> addCriterion(Criterion criterion) {
		criteria.add(criterion);
		return this;
	}

	List<Criterion> getFilterItems() {
		return criteria;
	}
}
