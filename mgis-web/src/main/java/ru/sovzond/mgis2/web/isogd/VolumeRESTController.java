package ru.sovzond.mgis2.web.isogd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.isogd.Book;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.business.BookBean;
import ru.sovzond.mgis2.isogd.business.VolumeBean;

import javax.transaction.Transactional;
import java.io.Serializable;

@RestController
@RequestMapping("/isogd/volumes")
@Scope("session")
public class VolumeRESTController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1275669415070078631L;

	@Autowired
	private VolumeBean volumeBean;

	@Autowired
	private BookBean bookBean;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PageableContainer<Volume> list(@RequestParam("bookId") Long bookId, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		Book book = bookBean.readBook(bookId);
		return volumeBean.pageVolumes(book, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Volume save(@PathVariable("id") Long id, @RequestBody Volume volume) {
		Volume volume2;
		if (id == 0) {
			volume2 = new Volume();
			volume2.setBook(bookBean.readBook(volume.getBook().getId()));
		} else {
			volume2 = volumeBean.readVolume(id);
		}
		volume2.setName(volume.getName());
		volumeBean.save(volume2);
		return volume2.clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Volume read(@PathVariable Long id) {
		return volumeBean.readVolume(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Transactional
	public void delete(@PathVariable Long id) {
		volumeBean.delete(volumeBean.readVolume(id));
	}

}
