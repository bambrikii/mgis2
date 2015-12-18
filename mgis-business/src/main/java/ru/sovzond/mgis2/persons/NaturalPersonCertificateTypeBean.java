package ru.sovzond.mgis2.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.registers.persons.NaturalPersonCertificateType;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by donchenko-y on 12/17/15.
 */
@Service
public class NaturalPersonCertificateTypeBean extends CRUDBeanBase<NaturalPersonCertificateType> {

	@Autowired
	private NaturalPersonCertificateTypeDao dao;

	@Override
	protected IPageableDAOBase<NaturalPersonCertificateType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<NaturalPersonCertificateType> getIIdentifiableDao() {
		return dao;
	}

}
