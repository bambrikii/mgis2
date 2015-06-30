package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.SectionDao;
import ru.sovzond.mgis2.isogd.document.CommonPartDao;
import ru.sovzond.mgis2.isogd.document.SpecialPartDao;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionBean {

	@Autowired
	private SectionDao sectionDao;


	@Autowired
	private CommonPartDao commonPartDao;

	@Autowired
	private SpecialPartDao specialPartDao;

	public Section readSection(Long id) {
		return sectionDao.findById(id);
	}

	public void save(Section section) {
		sectionDao.save(section);
	}

	public void delete(Section section) {
		sectionDao.delete(section);
	}

	public PageableContainer<Section> pageSections(int first, int max) {
		List<Section> sections = sectionDao.list(first, max).stream().map(section -> section.clone()).collect(Collectors.toList());
		return new PageableContainer<>(sections, sectionDao.count(), first, max);
	}

}
