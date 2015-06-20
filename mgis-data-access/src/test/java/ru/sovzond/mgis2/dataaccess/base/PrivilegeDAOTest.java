package ru.sovzond.mgis2.dataaccess.base;

import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.sovzond.mgis2.authentication.dataaccess.IPrivilegeDAO;
import ru.sovzond.mgis2.authentication.model.Privilege;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class PrivilegeDAOTest {

	@Autowired
	private IPrivilegeDAO privilegeDAO;

	@Test
	@Transactional
	public void testCRUD() {
		String name = "testPrivilege-" + UUID.randomUUID().toString();

		Privilege testPrivilege = new Privilege();
		testPrivilege.setName(name);
		privilegeDAO.save(testPrivilege);
		Assert.assertNotNull(testPrivilege.getId());
		Assert.assertNotEquals(Long.valueOf(0), testPrivilege.getId());

		testPrivilege = privilegeDAO.findByName(name);
		Assert.assertNotNull(testPrivilege);

		privilegeDAO.delete(testPrivilege);
	}

}
