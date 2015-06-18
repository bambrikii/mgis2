package ru.sovzond.mgis2.isogd.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.PageableFilter;
import ru.sovzond.mgis2.isogd.Book;
import ru.sovzond.mgis2.isogd.BookDao;
import ru.sovzond.mgis2.isogd.Document;
import ru.sovzond.mgis2.isogd.DocumentDao;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.SectionDao;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.VolumeDao;

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
		sectionDao.persist(section);
	}

	public void delete(Section section) {
		sectionDao.delete(section);
	}

	public List<Section> listSections(int first, int max) {
		return sectionDao.list(first, max);
	}

	public Number sectionCount() {
		return sectionDao.count();
	}

	public PageableContainer<Section> pageSections(int first, int max) {
		List<Section> sections = new ArrayList<Section>();
		for (Section section : sectionDao.list(first, max)) {
			sections.add(CloneManager.clone(section));
		}
		return new PageableContainer<Section>(sections, sectionDao.count());
	}

	public Volume readVolume(Long id) {
		return volumeDao.findById(id);
	}

	public void save(Volume volume) {
		volumeDao.persist(volume);
	}

	public void delete(Volume volume) {
		volumeDao.delete(volume);
	}

	public List<Volume> listVolumes(int first, int max) {
		return volumeDao.list(first, max);
	}

	public Number volumeCount() {
		return volumeDao.count();
	}

	public PageableContainer<Volume> pageVolumes(Book book, int first, int max) {
		List<Volume> list = new ArrayList<Volume>();
		PageableFilter<Volume> filter = volumeDao.createFilter(book);
		for (Volume volume : volumeDao.list(first, max, filter)) {
			list.add(CloneManager.clone(volume));
		}
		return new PageableContainer<Volume>(list, volumeDao.count(filter));
	}

	public Book readBook(Long id) {
		return bookDao.findById(id);
	}

	public void save(Book book) {
		bookDao.persist(book);
	}

	public void delete(Book book) {
		bookDao.delete(book);
	}

	public List<Book> listBooks(int first, int max) {
		return bookDao.list(first, max);
	}

	public Number bookCount() {
		return bookDao.count();
	}

	public PageableContainer<Book> pageBooks(Section section, int first, int max) {
		List<Book> books = new ArrayList<Book>();
		PageableFilter<Book> filter = bookDao.createFilter(section);
		for (Book book : bookDao.list(first, max, filter)) {
			books.add(CloneManager.clone(book));
		}
		return new PageableContainer<Book>(books, bookDao.count(filter));
	}

	public Document readDocument(Long id) {
		return documentDao.findById(id);
	}

	public void save(Document document) {
		documentDao.persist(document);
	}

	public void delete(Document document) {
		documentDao.delete(document);
	}

	public List<Document> listDocuments(int first, int max) {
		return documentDao.list(first, max);
	}

	public Number documentsCount() {
		return documentDao.count();
	}

	public PageableContainer<Document> pageDocuments(Volume volume, int first, int max) {
		List<Document> documents = new ArrayList<Document>();
		PageableFilter<Document> filter = documentDao.createFilter(volume);
		for (Document document : documentDao.list(first, max, filter)) {
			documents.add(CloneManager.clone(document));
		}
		return new PageableContainer<Document>(documents, documentDao.count(filter));
	}

}
