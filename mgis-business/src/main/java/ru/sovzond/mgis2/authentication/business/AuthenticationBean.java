package ru.sovzond.mgis2.authentication.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.authentication.dataaccess.IPrivilegeDAO;
import ru.sovzond.mgis2.authentication.dataaccess.IUserDAO;
import ru.sovzond.mgis2.authentication.model.Group;
import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.authentication.model.User;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthenticationBean {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IPrivilegeDAO privilegeDAO;

	public void authenticate(String username, String password) {

	}

	public User findUserByName(String username) {
		return userDAO.findByName(username);
	}

	public List<User> findUsers() {
		return userDAO.pager(PagerFactory.createDefault(User.class, -1, -1)).list();
	}

	public List<Privilege> loadPrivileges(String username) {
		return loadPrivileges(findUserByName(username));
	}

	public List<Privilege> loadPrivileges(User user) {
		Set<Privilege> privileges = new HashSet<>();
		privileges.addAll(user.getPrivileges());
		for (Group group : user.getGroups()) {
			loadPrivileges(privileges, group);
		}
		List<Privilege> result = new ArrayList<>();
		result.addAll(privileges);
		return result;
	}

	private void loadPrivileges(Set<Privilege> privileges, Group group) {
		privileges.addAll(group.getPrivileges());
		group.getChildGroups().stream().forEach(childGroup -> loadPrivileges(privileges, childGroup));
	}
}
