package ru.sovzond.mgis2.national_classifiers;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.registers.national_classifiers.OKOPF;

/**
 * Created by Alexander Arakelyan on 14.09.15.
 */
@Repository
public class OKOPFDao extends CRUDDaoBase<OKOPF> {
	public PagerBuilderBase<OKOPF> createFilter(String name, String orderBy, int first, int max) {
		return new OKOPFFilter(name, orderBy, first, max);
	}

	private class OKOPFFilter extends NameCodePagerBuilder<OKOPF> {
		private OKOPFFilter(String name, String orderBy, int first, int max) {
			super(name, orderBy, first, max);
		}
	}
}
