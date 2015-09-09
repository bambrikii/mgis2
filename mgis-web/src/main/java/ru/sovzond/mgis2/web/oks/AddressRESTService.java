package ru.sovzond.mgis2.web.oks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.oks.AddressBean;
import ru.sovzond.mgis2.registers.oks.OKSAddress;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by Alexander Arakelyan on 30/08/15.
 */
@RestController
@RequestMapping("/addresses")
@Scope("session")
public class AddressRESTService implements Serializable {
	@Autowired
	private AddressBean addressBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Address> list(@RequestParam(value = "orderBy", defaultValue = "") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return addressBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Address save(@PathVariable("id") Long id, @RequestBody OKSAddress address) {
		Address address1;
		if (id == 0) {
			address1 = new Address();
		} else {
			address1 = addressBean.load(id);
		}
		return addressBean.save(address1).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public Address read(@PathVariable("id") Long id) {
		return addressBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		addressBean.remove(addressBean.load(id));
	}
}
