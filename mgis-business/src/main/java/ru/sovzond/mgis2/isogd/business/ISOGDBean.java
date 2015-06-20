package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableFilter;
import ru.sovzond.mgis2.isogd.*;
import ru.sovzond.mgis2.isogd.document.Document;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ISOGDBean {

	@Autowired
	private SectionDao sectionDao;

	@Autowired
	private VolumeDao volumeDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private DocumentDao documentDao;

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
		List<Section> sections = sectionDao.list(first, max).stream().map(CloneManager::clone).collect(Collectors.toList());
		return new PageableContainer<>(sections, sectionDao.count());
	}

	public Volume readVolume(Long id) {
		return volumeDao.findById(id);
	}

	public void save(Volume volume) {
		volumeDao.save(volume);
	}

	public void delete(Volume volume) {
		volumeDao.delete(volume);
	}

	public PageableContainer<Volume> pageVolumes(Book book, int first, int max) {
		PageableFilter<Volume> filter = volumeDao.createFilter(book);
		List<Volume> list = volumeDao.list(first, max, filter).stream().map(CloneManager::clone).collect(Collectors.toList());
		return new PageableContainer<>(list, volumeDao.count(filter));
	}

	public Book readBook(Long id) {
		return bookDao.findById(id);
	}

	public void save(Book book) {
		bookDao.save(book);
	}

	public void delete(Book book) {
		bookDao.delete(book);
	}

	public PageableContainer<Book> pageBooks(Section section, int first, int max) {
		PageableFilter<Book> filter = bookDao.createFilter(section);
		List<Book> books = bookDao.list(first, max, filter).stream().map(CloneManager::clone).collect(Collectors.toList());
		return new PageableContainer<>(books, bookDao.count(filter));
	}

	public Document readDocument(Long id) {
		return documentDao.findById(id);
	}

	public void save(Document document) {
		documentDao.save(document);
	}

	public void delete(Document document) {
		documentDao.delete(document);
	}

	public PageableContainer<Document> pageDocuments(Volume volume, int first, int max) {
		PageableFilter<Document> filter = documentDao.createFilter(volume);
		List<Document> documents = documentDao.list(first, max, filter).stream().map(CloneManager::clone).collect(Collectors.toList());
		return new PageableContainer<>(documents, documentDao.count(filter));
	}

}
