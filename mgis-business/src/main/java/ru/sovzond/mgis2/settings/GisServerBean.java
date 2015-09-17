package ru.sovzond.mgis2.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 16.09.15.
 */
@Service
public class GisServerBean extends CRUDBeanBase<GisServer> {
	@Autowired
	private GisServerDao dao;

	@Override
	protected IPageableDAOBase<GisServer> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<GisServer> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<GisServer> list(String code, String orderBy, int first, int max) {
		Pageable<GisServer> pager = dao.pager(dao.createFilter(orderBy, first, max, code));
		return new PageableContainer<GisServer>(pager.list().stream().map(GisServer::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
