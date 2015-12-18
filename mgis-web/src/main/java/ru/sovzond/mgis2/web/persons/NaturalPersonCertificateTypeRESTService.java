package ru.sovzond.mgis2.web.persons;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.registers.persons.NaturalPersonCertificateType;
import ru.sovzond.mgis2.persons.NaturalPersonCertificateTypeBean;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by donchenko-y on 12/17/15.
 */

@RestController
@RequestMapping("/persons/natural-persons/certificate-types")
@Scope("session")
public class NaturalPersonCertificateTypeRESTService implements Serializable {

	@Autowired
	private NaturalPersonCertificateTypeBean naturalPersonCertificateBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<NaturalPersonCertificateType> list(@RequestParam(defaultValue = "") String name, @RequestParam(value = "orderBy", defaultValue = "name") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return naturalPersonCertificateBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public NaturalPersonCertificateType read(@PathVariable Long id) {
		return naturalPersonCertificateBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		naturalPersonCertificateBean.remove(naturalPersonCertificateBean.load(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public NaturalPersonCertificateType save(@PathVariable Long id, @RequestBody NaturalPersonCertificateType naturalPersonCertificateType) {
		NaturalPersonCertificateType naturalPersonCertificateType2;
		if (id == 0) {
			naturalPersonCertificateType2 = new NaturalPersonCertificateType();
		} else {
			naturalPersonCertificateType2 = naturalPersonCertificateBean.load(id);
		}
		BeanUtils.copyProperties(naturalPersonCertificateType, naturalPersonCertificateType2, new String[]{"id"});
		naturalPersonCertificateBean.save(naturalPersonCertificateType2);
		return naturalPersonCertificateType2.clone();
	}
}
