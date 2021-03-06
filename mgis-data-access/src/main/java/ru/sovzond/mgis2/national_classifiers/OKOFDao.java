package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.national_classifiers.OKOF;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 10.11.15.
 * <p/>
 * ОКОФ
 */
@Repository
public class OKOFDao extends CRUDDaoBase<OKOF> {

	public PagerBuilderBase<OKOF> createFilter(String code, String name, String[] codeTemplates, String orderBy, int first, int max) {
		return new OKOFFilter(code, name, codeTemplates, orderBy, first, max);
	}

	private class OKOFFilter extends PagerBuilderCriteria<OKOF> {
		private String code;
		private String name;
		private String[] codeTemplates;

		private OKOFFilter(String code, String name, String[] codeTemplates, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.code = code;
			this.name = name;
			this.codeTemplates = codeTemplates;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (code != null && code.length() > 0) {
				criteria.add(Restrictions.like("code", "%" + code + "%"));
			}
			if (name != null && name.length() > 0) {
				criteria.add(Restrictions.like("name", "%" + name + "%").ignoreCase());
			}
			if (codeTemplates != null && codeTemplates.length > 0) {
				criteria.add(Restrictions.or(
						Arrays.asList(codeTemplates)
								.stream()
								.map(codeTemplate -> Restrictions.like("code", codeTemplate + "%"))
								.collect(Collectors.toList())
								.toArray(new Criterion[codeTemplates.length])
				));
			}

		}
	}
}
