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
import ru.sovzond.mgis2.isogd.Section;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.business.ISOGDBean;

@RestController
@RequestMapping("/isogd/volumes")
@Scope("session")
public class VolumeRESTController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1275669415070078631L;

	@Autowired
	private ISOGDBean isogdBean;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public PageableContainer<Volume> list(@RequestParam("sectionId") Long sectionId, @RequestParam(defaultValue = "0") int first,
			@RequestParam(defaultValue = "0") int max) {
		Section section = isogdBean.readSection(sectionId);
		return isogdBean.pageVolumes(section, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Volume save(@PathVariable("id") Long id, @RequestBody Volume volume) {
		Volume volume2;
		if (id == 0) {
			volume2 = new Volume();
			volume2.setSection(isogdBean.readSection(volume.getSection().getId()));
		} else {
			volume2 = isogdBean.readVolume(id);
		}
		volume2.setName(volume.getName());
		isogdBean.save(volume2);
		return CloneManager.clone(volume2);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Volume read(@PathVariable Long id) {
		Volume volume = isogdBean.readVolume(id);
		return CloneManager.clone(volume);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Transactional
	public void delete(@PathVariable Long id) {
		isogdBean.delete(isogdBean.readVolume(id));
	}

}
