package ru.sovzond.mgis2.dataaccess.base.impl;

import org.hibernate.criterion.Restrictions;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
public class PageableCRUDDaoBase<T> extends PageableDAOBase<T> implements IIdentifiableDao<T> {

    public static final String ID = "id";
    public static final String NO_IDENTIFIERS_PROVIDED = "NO_IDENTIFIERS_PROVIDED";

    @Override
    public T findById(Long id) {
        return (T) createCriteria().add(Restrictions.eq(ID, id)).uniqueResult();
    }

    @Override
    public List<T> findByIds(List<Long> ids) {
        if (ids.size() == 0) {
            throw new IllegalArgumentException(NO_IDENTIFIERS_PROVIDED);
        }
        return createCriteria().add(Restrictions.in(ID, ids)).list();
    }
}
