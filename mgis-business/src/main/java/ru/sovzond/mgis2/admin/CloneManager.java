package ru.sovzond.mgis2.admin;

import ru.sovzond.mgis2.authentication.model.Group;
import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.authentication.model.User;

import java.util.stream.Collectors;

/**
 * Created by asd on 20/06/15.
 */
public class CloneManager {

	public static User clone(User user) {
		User user2 = new User();
		user2.setId(user.getId());
		user2.setActive(user.isActive());
		user2.setPassword(user.getPassword());
		user2.setUsername(user.getUsername());
		user2.setGroups(user.getGroups().stream().map(CloneManager::clone).collect(Collectors.toList()));
		user2.setPrivileges(user.getPrivileges().stream().map(CloneManager::clone).collect(Collectors.toList()));
		return user2;
	}

	public static Group clone(Group group) {
		Group group2 = new Group();
		group2.setId(group.getId());
		group2.setGroupname(group.getGroupname());
		group2.setPrivileges(group.getPrivileges().stream().map(CloneManager::clone).collect(Collectors.toList()));
		group2.setChildGroups(group.getChildGroups().stream().map(CloneManager::clone).collect(Collectors.toList()));
		return group2;
	}

	public static Privilege clone(Privilege privilege) {
		Privilege privilege2 = new Privilege();
		privilege2.setId(privilege.getId());
		privilege2.setName(privilege.getName());
		return privilege2;
	}
}
