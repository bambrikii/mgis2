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
public class LayerBean extends CRUDBeanBase<Layer> {
	@Autowired
	private LayerDao dao;

	@Override
	protected IPageableDAOBase<Layer> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Layer> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<Layer> list(String code, String orderBy, int first, int max) {
		Pageable<Layer> pager = dao.pager(dao.createFilter(orderBy, first, max, code));
		return new PageableContainer<Layer>(pager.list().stream().map(Layer::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
