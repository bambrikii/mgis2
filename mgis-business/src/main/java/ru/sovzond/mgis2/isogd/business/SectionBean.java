package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerFactory;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.SectionDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionBean extends CRUDBeanBase<Section> {

	@Autowired
	private SectionDao sectionDao;

	@Override
	protected IPageableDAOBase<Section> getPageableDao() {
		return sectionDao;
	}

	@Override
	protected IIdentifiableDao<Section> getIIdentifiableDao() {
		return sectionDao;
	}

	public Section readSection(Long id) {
		return sectionDao.findById(id);
	}

	public Section save(Section section) {
		sectionDao.save(section);
		return section;
	}

	public void delete(Section section) {
		sectionDao.delete(section);
	}

	public PageableContainer<Section> pageSections(String orderBy, int first, int max) {
		Pageable<Section> pager = sectionDao.pager(PagerFactory.createDefault(persistentClass, orderBy, first, max));
		List<Section> sections = pager.list().stream().map(section -> section.clone()).collect(Collectors.toList());
		return new PageableContainer<>(sections, pager.count(), first, max);
	}

}
