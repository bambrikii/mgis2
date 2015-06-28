package ru.sovzond.mgis2.admin;

import org.junit.Assert;
import org.junit.Test;
import ru.sovzond.mgis2.authentication.model.Group;
import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.authentication.model.User;

public class AuthenticationCloneTest {

	@Test
	public void testUser() {
		User user = new User();
		User user2 = user.clone();
		Assert.assertEquals(user, user2);
	}

	@Test
	public void testGroup() {
		Group group = new Group();
		Group group2 = group.clone();
		Assert.assertEquals(group, group2);
	}

	@Test
	public void testPrivilege() {
		Privilege privilege = new Privilege();
		Privilege privilege2 = privilege.clone();
		Assert.assertEquals(privilege, privilege2);
	}
}
