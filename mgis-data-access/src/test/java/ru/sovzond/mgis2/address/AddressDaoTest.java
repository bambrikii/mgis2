package ru.sovzond.mgis2.address;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sovzond.mgis2.dataaccess.base.HibernateConfiguration;

/**
 * Created by Alexander Arakelyan on 21/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class AddressDaoTest {

	@Autowired
	private AddressDao dao;

}
