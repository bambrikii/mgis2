package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.isogd.Book;
import ru.sovzond.mgis2.isogd.BookDao;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 */
@Service
public class BookBean {

	@Autowired
	private BookDao dao;

	public Book readBook(Long id) {
		return dao.findById(id);
	}

	public void save(Book book) {
		dao.save(book);
	}

	public void delete(Book book) {
		dao.delete(book);
	}

	public PageableContainer<Book> pageBooks(Section section, int first, int max) {
		Pageable<Book> pager = dao.pager(dao.createFilter(section, first, max));
		List<Book> books = pager.list().stream().map(book -> book.clone()).collect(Collectors.toList());
		return new PageableContainer<>(books, pager.count(), first, max);
	}

	public List<DocumentObject> listDocumentObjectsBySection(Section section) {
		return dao.listAvailableDocumentObjects(section);
	}

}
