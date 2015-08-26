package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.registers.lands.Land;

import java.util.stream.Collectors;

@Service
public class LandBean extends CRUDBeanBase<Land> {

	@Autowired
	private LandDao dao;

	@Override
	protected IPageableDAOBase<Land> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Land> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<Land> list(String cadastralNumber, Integer[] ids, String orderBy, int first, int max) {
		Pageable<Land> lands = dao.pager(dao.createFilter(cadastralNumber, ids, orderBy, first, max));
		return new PageableContainer<>(lands.list().stream().map(Land::clone).collect(Collectors.toList()), lands.count(), first, max);
	}

}
