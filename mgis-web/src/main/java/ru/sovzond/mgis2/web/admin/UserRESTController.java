package ru.sovzond.mgis2.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.admin.CloneManager;
import ru.sovzond.mgis2.admin.GroupBean;
import ru.sovzond.mgis2.admin.PrivilegeBean;
import ru.sovzond.mgis2.admin.UserBean;
import ru.sovzond.mgis2.authentication.model.User;
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
public class UserRESTController implements IRESTController<User>, Serializable {

	@Autowired
	private UserBean userBean;

	@Autowired
	private PrivilegeBean privilegeBean;

	@Autowired
	private GroupBean groupBean;

	@Override
	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<User> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		PageableContainer<User> pager = userBean.list(first, max);
		return new PageableContainer<>(pager.getList().stream().map(CloneManager::clone).collect(Collectors.toList()), pager.getCount());
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@Transactional
	public User save(@PathVariable("id") Long id, @RequestBody User user) {
		User user2 = userBean.load(id);
		user2.setId(user.getId());
		user2.setUsername(user.getUsername());
		user2.setPassword(user.getPassword());
		user2.setActive(user.isActive());
		user2.setPrivileges(privilegeBean.load(user.getPrivileges().stream().map(privilege -> privilege.getId()).collect(Collectors.toList())));
		user2.setGroups(groupBean.load(user.getGroups().stream().map(group -> group.getId()).collect(Collectors.toList())));
		userBean.save(user2);
		return CloneManager.clone(user2);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public User read(@PathVariable Long id) {
		return CloneManager.clone(userBean.load(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	@Override
	public void delete(@PathVariable Long id) {
		userBean.remove(userBean.load(id));
	}
}
