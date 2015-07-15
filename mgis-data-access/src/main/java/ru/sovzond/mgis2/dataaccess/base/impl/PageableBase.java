package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

public abstract class PageableBase<T> {

    public static final String ORDER_BY_COLUMN_SPLIT = "\\s+";
    public static final String DESC_ORDER_MARK = "desc";
    public static final int DEFAULT_LIMIT = -1;

    protected String[] orderBy;
    protected int first = DEFAULT_LIMIT;
    protected int max = DEFAULT_LIMIT;

    protected String[] prepareOrderBy(String orderBy) {
        if (orderBy != null && !"".equals(orderBy.trim())) {
            return orderBy.split(",");
        }
        return null;
    }

    protected void apply(Criteria criteria) {
        applyFilter(criteria);
        applyOrder(criteria);
        applyLimit(criteria);
    }

    protected abstract void applyFilter(Criteria criteria);

    private void applyOrder(Criteria criteria) {
        if (orderBy != null && orderBy.length > 0) {
            for (String orderByString : orderBy) {
                String[] orderByArr = orderByString.split(ORDER_BY_COLUMN_SPLIT);
                criteria.addOrder(((orderByArr.length > 1) && orderByArr[1].toLowerCase().indexOf(DESC_ORDER_MARK) > -1) ? Order.desc(orderByArr[0]) : Order.asc(orderByArr[0]));
            }
        }
    }

    private void applyLimit(Criteria criteria) {
        if (first != DEFAULT_LIMIT) {
            criteria.setFirstResult(first);
        }
        if (max != DEFAULT_LIMIT) {
            criteria.setMaxResults(max);
        }
    }
}
