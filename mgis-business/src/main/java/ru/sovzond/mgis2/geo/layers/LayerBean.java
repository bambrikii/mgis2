package ru.sovzond.mgis2.geo.layers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 10/10/15.
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

	public PageableContainer<Layer> list(Layer parent, String orderBy, int first, int max) {
		Pageable<Layer> pager = dao.pager(dao.createPager(parent, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(Layer::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
