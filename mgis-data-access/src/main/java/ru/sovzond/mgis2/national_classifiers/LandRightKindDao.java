package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 */
@Repository
public class LandRightKindDao extends CRUDDaoBase<LandRightKind> {
	public LandRightKind findByClassificationCode(String classificationCode) {
		return (LandRightKind) getSession().createCriteria(persistentClass).add(Restrictions.eq("classificationCode", classificationCode)).uniqueResult();
	}
}
