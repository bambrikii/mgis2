package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 04.08.15.
 */
@Service
public class TerritorialZoneTypeBean extends CRUDBeanBase<TerritorialZoneType> {

	@Autowired
	private TerritorialZoneTypeDao dao;

	@Override

	protected IPageableDAOBase<TerritorialZoneType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<TerritorialZoneType> getIIdentifiableDao() {
		return dao;
	}

	public List<TerritorialZoneType> findByNameSubstring(String nameSubstring) {
		return dao.findByNameSubstring(nameSubstring);
	}

	public TerritorialZoneType findByCode(String code) {
		return dao.findByCode(code);
	}
}
