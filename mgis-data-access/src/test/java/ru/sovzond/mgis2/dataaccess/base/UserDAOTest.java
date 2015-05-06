package ru.sovzond.mgis2.dataaccess.base;

import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.sovzond.mgis2.authentication.dataaccess.IPrivilegeDAO;
import ru.sovzond.mgis2.authentication.dataaccess.IUserDAO;
import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.authentication.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class UserDAOTest {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IPrivilegeDAO privilegeDAO;

	private Privilege priv1;
	private Privilege priv2;

	@Before
	public void before() {
		priv1 = createPrivilege();
		priv2 = createPrivilege();
	}

	@After
	public void after() {
		deletePrivilege(priv1);
		deletePrivilege(priv2);
	}

	@Test
	@Transactional
	public void testCRUD() {
		String username = "testUser-" + UUID.randomUUID().toString();

		User testUser = new User();
		testUser.setUsername(username);
		testUser.setPassword("testPassword");
		testUser.setActive(true);
		userDAO.persist(testUser);
		Assert.assertNotNull(testUser.getId());
		Assert.assertNotEquals(Long.valueOf(0), testUser.getId());

		testUser = userDAO.findByName(username);
		Assert.assertNotNull(testUser);

		userDAO.delete(testUser);
	}

	@Test
	@Transactional
	public void testAddPrivileges() {

		String username = "testUser-" + UUID.randomUUID().toString();
		User testUser = new User();
		testUser.setUsername(username);
		testUser.setPassword("testPassword");
		testUser.setActive(true);
		testUser.addPrivilege(priv1);
		testUser.addPrivilege(priv2);
		userDAO.persist(testUser);

		testUser = userDAO.findByName(username);
		Assert.assertNotNull(testUser);
		Assert.assertTrue(testUser.containsPrivilege(priv1));
		Assert.assertTrue(testUser.containsPrivilege(priv2));

		userDAO.delete(testUser);

	}

	@Test
	@Transactional
	public void testRemovePrivileges() {
		String username = "testUser-" + UUID.randomUUID().toString();
		User testUser = new User();
		testUser.setUsername(username);
		testUser.setPassword("testPassword");
		testUser.setActive(true);
		testUser.addPrivilege(priv1);
		userDAO.persist(testUser);
		Assert.assertNotNull(testUser);

		testUser = userDAO.findByName(username);
		Assert.assertTrue(testUser.containsPrivilege(priv1));

		testUser.removePrivilege(priv1);
		userDAO.persist(testUser);
		Assert.assertFalse(testUser.containsPrivilege(priv1));

		userDAO.delete(testUser);
	}

	private Privilege createPrivilege() {
		Privilege testPrivilege = new Privilege();
		testPrivilege.setName("testPrivilege-" + UUID.randomUUID().toString());
		privilegeDAO.persist(testPrivilege);
		return testPrivilege;
	}

	private void deletePrivilege(Privilege privilege) {
		privilegeDAO.delete(privilege);
	}
}
