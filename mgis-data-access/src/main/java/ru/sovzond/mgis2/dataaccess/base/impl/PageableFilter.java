package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Criteria;

public abstract class PageableFilter<T> {
    protected abstract void apply(Criteria criteria);
}
