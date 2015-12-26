package ru.sovzond.mgis2.capital_construct;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.capital_constructs.ConstructionType;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;

/**
 * Created by Alexander Arakelyan on 12/11/15.
 */
@Repository
public class ConstructTypeDao extends CRUDDaoBase<ConstructionType> {
	public ConstructionType findByCode(String code) {
		return (ConstructionType) createCriteria().add(Restrictions.eq("code", code)).uniqueResult();
	}
}
