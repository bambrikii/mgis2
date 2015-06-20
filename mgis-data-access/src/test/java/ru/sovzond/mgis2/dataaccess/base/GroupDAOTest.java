package ru.sovzond.mgis2.dataaccess.base;

import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.sovzond.mgis2.authentication.dataaccess.IGroupDAO;
import ru.sovzond.mgis2.authentication.dataaccess.IPrivilegeDAO;
import ru.sovzond.mgis2.authentication.dataaccess.IUserDAO;
import ru.sovzond.mgis2.authentication.model.Group;
import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.authentication.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class GroupDAOTest {

	@Autowired
	private IGroupDAO groupDAO;

	@Autowired
	private IPrivilegeDAO privilegeDAO;

	@Autowired
	private IUserDAO userDAO;

	@Test
	@Transactional
	public void testCRUD() {
		String groupname1 = "testGroup-" + UUID.randomUUID().toString();
		Group testGroup = new Group();
		testGroup.setGroupname(groupname1);
		groupDAO.save(testGroup);
		Assert.assertTrue(testGroup.getId() > 0);

		testGroup = groupDAO.findByName(groupname1);
		Assert.assertNotNull(testGroup);

		groupDAO.delete(testGroup);

	}

	@Test
	@Transactional
	public void testParentGroup() {
		String groupname1 = "testGroup-" + UUID.randomUUID().toString();
		String groupname2 = "testGroup-" + UUID.randomUUID().toString();

		Group testGroup1 = null;
		Group testGroup2 = null;
		try {
			testGroup1 = new Group();
			testGroup1.setGroupname(groupname1);
			groupDAO.save(testGroup1);

			// adding parent
			testGroup2 = new Group();
			testGroup2.setGroupname(groupname2);
			testGroup2.setParentGroup(testGroup1);
			testGroup1.getChildGroups().add(testGroup2);
			// groupDAO.save(testGroup1);
			groupDAO.save(testGroup2);

			Group testGroup1f2 = groupDAO.findByName(groupname1);
			Assert.assertTrue("Child group not int childGroup collection!", testGroup1f2.getChildGroups().contains(testGroup2));

			Group testGroup2f2 = groupDAO.findByName(groupname2);
			Assert.assertTrue("Parent group not set.", testGroup2f2.getParentGroup().getGroupname().equals(testGroup1.getGroupname()));

			// removing parent
			testGroup1f2.getChildGroups().remove(testGroup2);
			testGroup2.setParentGroup(null);
			groupDAO.save(testGroup1f2);
			Group testGroup1f3 = groupDAO.findByName(groupname1);
			Assert.assertTrue("Child group still not removed!", !testGroup1f3.getChildGroups().contains(testGroup2));

			Group testGroup2f3 = groupDAO.findByName(groupname2);
			Assert.assertTrue("Parent group still not removed.", testGroup2f3.getParentGroup() == null);

		} finally {
			if (testGroup1 != null) {
				groupDAO.delete(testGroup1);
			}
			if (testGroup2 != null) {
				groupDAO.delete(testGroup2);
			}
		}
	}

	@Test
	@Transactional
	public void testPrivileges() {
		String groupname1 = "testGroup-" + UUID.randomUUID().toString();
		String privilegename1 = "testPrivilege-" + UUID.randomUUID().toString();

		Group group = null;
		Privilege privilege = null;

		try {
			privilege = new Privilege();
			privilege.setName(privilegename1);
			privilegeDAO.save(privilege);

			group = new Group();
			group.setGroupname(groupname1);
			group.getPrivileges().add(privilege);
			groupDAO.save(group);

			Group group2 = groupDAO.findByName(groupname1);
			Assert.assertTrue(group2.getPrivileges().contains(privilege));

			group2.getPrivileges().remove(privilege);
			groupDAO.save(group);

			Group group3 = groupDAO.findByName(groupname1);
			Assert.assertTrue(!group3.getPrivileges().contains(privilege));
		} finally {
			if (group != null) {
				groupDAO.delete(group);
			}
			if (privilege != null) {
				privilegeDAO.delete(privilege);
			}
		}
	}

	@Test
	@Transactional
	public void testUsers() {
		String groupname1 = "testGroup-" + UUID.randomUUID().toString();
		String username1 = "testUser-" + UUID.randomUUID().toString();

		Group group = null;
		User user = null;

		try {
			user = new User();
			user.setUsername(username1);
			user.setPassword(username1);
			user.setActive(true);
			userDAO.save(user);

			group = new Group();
			group.setGroupname(groupname1);
			user.getGroups().add(group);
			group.getUsers().add(user);
			groupDAO.save(group);

			Group group2 = groupDAO.findByName(groupname1);
			Assert.assertTrue(group2.getUsers().contains(user));
			User user2 = group2.getUsers().get(0);
			Assert.assertTrue(user2.getGroups().contains(group2));

			group2.getUsers().remove(user);
			user2.getGroups().remove(group2);
			groupDAO.save(group);

			Group group3 = groupDAO.findByName(groupname1);
			Assert.assertTrue(!group3.getUsers().contains(user));
			User user3 = userDAO.findByName(username1);
			Assert.assertTrue(!user3.getGroups().contains(group3));

		} finally {
			if (group != null) {
				groupDAO.delete(group);
			}
			if (user != null) {
				userDAO.delete(user);
			}
		}
	}
}
