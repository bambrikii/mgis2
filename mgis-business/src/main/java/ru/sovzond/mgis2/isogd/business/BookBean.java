package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableFilter;
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
	private BookDao bookDao;

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
		List<Book> books = bookDao.list(first, max, filter).stream().map(book -> book.clone()).collect(Collectors.toList());
		return new PageableContainer<>(books, bookDao.count(filter), first, max);
	}

	public List<DocumentObject> listDocumentObjectsBySection(Section section) {
		return bookDao.listAvailableDocumentObjects(section);
	}

}
