package ru.sovzond.mgis2.kladr;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.sovzond.mgis2.dataaccess.base.HibernateConfiguration;

import java.util.List;

import static ru.sovzond.mgis2.kladr.KLADRLocalityDaoTest.*;

/**
 * Created by Alexander Arakelyan on 21/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class KLADRStreetDaoTest {

	@Autowired
	private KLADRStreetDao dao;


	@Test
	@Transactional
	public void testFindStreet() {
		List<KLADRStreet> list = dao.findStreet(LOCALITY_CODE, STREET_NAME, STREET_TYPE);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(STREET_CODE, list.get(0).getCode());
	}
}
