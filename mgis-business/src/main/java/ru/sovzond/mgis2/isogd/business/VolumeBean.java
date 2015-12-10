package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.isogd.Book;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.VolumeDao;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 */
@Service
public class VolumeBean extends CRUDBeanBase<Volume> {

	@Autowired
	private VolumeDao dao;

	@Override
	protected IPageableDAOBase<Volume> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Volume> getIIdentifiableDao() {
		return dao;
	}

	public Volume readVolume(Long id) {
		return dao.findById(id);
	}

	public PageableContainer<Volume> list(Book book, String orderBy, int first, int max) {
		Pageable<Volume> pager = dao.pager(dao.createFilter(book, orderBy, first, max));
		List<Volume> list = pager.list().stream().map(volume -> volume.clone()).collect(Collectors.toList());
		return new PageableContainer<>(list, pager.count(), first, max);
	}

}
