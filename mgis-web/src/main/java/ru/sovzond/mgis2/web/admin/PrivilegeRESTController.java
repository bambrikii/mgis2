package ru.sovzond.mgis2.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.admin.CloneManager;
import ru.sovzond.mgis2.admin.PrivilegeBean;
import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.web.IRESTController;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by asd on 20/06/15.
 */
@RestController
@RequestMapping("/admin")
public class PrivilegeRESTController implements IRESTController<Privilege>, Serializable {

	@Autowired
	private PrivilegeBean privilegeBean;

	@Override
	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Privilege> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		PageableContainer<Privilege> pager = privilegeBean.list(first, max);
		return new PageableContainer<>(pager.getList().stream().map(CloneManager::clone).collect(Collectors.toList()), pager.getCount());
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@Transactional
	public Privilege save(@PathVariable("id") Long id, @RequestBody Privilege privilege) {
		Privilege privilege2 = privilegeBean.load(id);
		privilege2.setId(privilege.getId());
		privilege2.setName(privilege.getName());
		privilegeBean.save(privilege2);
		return CloneManager.clone(privilege2);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public Privilege read(@PathVariable Long id) {
		return CloneManager.clone(privilegeBean.load(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	@Override
	public void delete(@PathVariable Long id) {
		privilegeBean.remove(privilegeBean.load(id));
	}
}
