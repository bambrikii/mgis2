package ru.sovzond.mgis2.web.isogd;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.isogd.Book;
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.business.BookBean;
import ru.sovzond.mgis2.isogd.business.SectionBean;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentObjectBean;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/isogd/books")
@Scope("session")
public class BookRESTController implements Serializable {

	private static final long serialVersionUID = 4539915548911543515L;

	@Autowired
	private SectionBean sectionBean;

	@Autowired
	private BookBean bookBean;

	@Autowired
	private DocumentObjectBean documentObjectBean;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PageableContainer<Book> list(@RequestParam("sectionId") Long sectionId, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "sortOrder") String orderBy) {
		Section section = sectionBean.readSection(sectionId);
		return bookBean.list(section, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Book save(@PathVariable Long id, @RequestBody Book book) {
		Book book2;
		if (id == 0) {
			book2 = new Book();
			book2.setSection(sectionBean.readSection(book.getSection().getId()));
		} else {
			book2 = bookBean.load(id);
		}
		BeanUtils.copyProperties(book, book2, new String[]{"id", "section", "documentObject", "volumes"});
		book2.setDocumentObject(documentObjectBean.load(book.getDocumentObject().getId()));
		bookBean.save(book2);
		return book2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Book read(@PathVariable Long id) {
		return bookBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		bookBean.remove(bookBean.load(id));
	}

	@RequestMapping(value = "/listDocumentObjectsBySectionId/{sectionId}")
	@Transactional
	public PageableContainer<DocumentObject> listDocumentObjectsBySectionId(@PathVariable Long sectionId) {
		return new PageableContainer<>(bookBean.listDocumentObjectsBySection(sectionBean.readSection(sectionId)).stream().map(documentObject -> documentObject.clone()).collect(Collectors.toList()));
	}

	@RequestMapping(value = "/swap-orders", method = RequestMethod.POST)
	@Transactional
	public void swapOrders(@RequestBody SwapIdPair pair) {
		Long sourceId = pair.getSourceId();
		Long targetId = pair.getTargetId();
		Book source = bookBean.load(sourceId);
		Book target = bookBean.load(targetId);
		Long sourceOrder = (source.getSortOrder() == null || source.getSortOrder() == 0) ? sourceId : source.getSortOrder();
		Long targetOrder = (target.getSortOrder() == null || target.getSortOrder() == 0) ? targetId : target.getSortOrder();

		source.setSortOrder(targetOrder);
		target.setSortOrder(sourceOrder);
		bookBean.save(source);
		bookBean.save(target);
	}

}
