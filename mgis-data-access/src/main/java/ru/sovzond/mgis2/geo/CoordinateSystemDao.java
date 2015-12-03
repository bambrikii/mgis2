package ru.sovzond.mgis2.geo;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;

/**
 * Created by Alexander Arakelyan on 05.10.15.
 */
@Repository
public class CoordinateSystemDao extends CRUDDaoBase<CoordinateSystem> {
	public CoordinateSystem findByCode(String code) {
		return (CoordinateSystem) getSession().createCriteria(persistentClass).add(Restrictions.eq("code", code)).uniqueResult();
	}
}
