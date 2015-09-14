package ru.sovzond.mgis2.national_classifiers;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.registers.national_classifiers.OKOGU;

/**
 * Created by Alexander Arakelyan on 14.09.15.
 */
@Repository
public class OKOGUDao extends CRUDDaoBase<OKOGU> {
	public PagerBuilderBase<OKOGU> createFilter(String name, String orderBy, int first, int max) {
		return new OKOGUFilter(name, orderBy, first, max);
	}

	private class OKOGUFilter extends NameCodePagerBuilder<OKOGU> {
		private OKOGUFilter(String name, String orderBy, int first, int max) {
			super(name, orderBy, first, max);
		}
	}
}
