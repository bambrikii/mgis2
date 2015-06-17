package ru.sovzond.mgis2.web.isogd;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.Book;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.business.ISOGDBean;

@RestController
@RequestMapping("/isogd/books")
@Scope("session")
public class BookRESTController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4539915548911543515L;

	@Autowired
	private ISOGDBean isogdBean;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PageableContainer<Book> list(@RequestParam("volumeId") Long volumeId, @RequestParam(defaultValue = "0") int first,
			@RequestParam(defaultValue = "0") int max) {
		Volume volume = isogdBean.readVolume(volumeId);
		return isogdBean.pageBooks(volume, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Book save(@PathVariable("id") Long id, @RequestBody Book book) {
		Book book2;
		if (id == 0) {
			book2 = new Book();
			book.setVolume(isogdBean.readVolume(book.getVolume().getId()));
		} else {
			book2 = isogdBean.readBook(id);
		}
		isogdBean.save(book2);
		return CloneManager.clone(book2);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Book read(@RequestParam Long id) {
		return isogdBean.readBook(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Transactional
	public void delete(@RequestParam Long id) {
		isogdBean.delete(isogdBean.readBook(id));
	}

}
