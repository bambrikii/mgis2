package ru.sovzond.mgis2.national_classifiers;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.registers.national_classifiers.OKVED;

/**
 * Created by Alexander Arakelyan on 14.09.15.
 */
@Repository
public class OKVEDDao extends CRUDDaoBase<OKVED> {
	public PagerBuilderBase<OKVED> createFilter(String name, String orderBy, int first, int max) {
		return new OKVEDFilter(name, orderBy, first, max);
	}

	private class OKVEDFilter extends NameCodePagerBuilder<OKVED> {
		private OKVEDFilter(String name, String orderBy, int first, int max) {
			super(name, orderBy, first, max);
		}
	}
}
