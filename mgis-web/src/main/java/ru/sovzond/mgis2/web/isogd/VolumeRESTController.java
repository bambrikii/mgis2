package ru.sovzond.mgis2.web.isogd;

import org.springframework.beans.BeanUtils;
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
	public PageableContainer<Volume> list(@RequestParam("bookId") Long bookId, @RequestParam(defaultValue = "sortOrder") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		Book book = bookBean.load(bookId);
		return volumeBean.list(book, orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Volume save(@PathVariable("id") Long id, @RequestBody Volume volume) {
		Volume volume2;
		if (id == 0) {
			volume2 = new Volume();
			volume2.setBook(bookBean.load(volume.getBook().getId()));
		} else {
			volume2 = volumeBean.readVolume(id);
		}
		BeanUtils.copyProperties(volume, volume2, new String[]{"id", "book", "documents"});
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
		volumeBean.remove(volumeBean.readVolume(id));
	}

	@RequestMapping(value = "/swap-orders", method = RequestMethod.POST)
	@Transactional
	public void swapOrders(@RequestBody SwapIdPair pair) {
		SwapManager.byOrder(pair, volumeBean);
	}

}
