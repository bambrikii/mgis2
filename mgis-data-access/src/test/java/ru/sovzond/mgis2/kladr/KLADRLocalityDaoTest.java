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

/**
 * Created by Alexander Arakelyan on 21/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class KLADRLocalityDaoTest {

	public static final String SUBJECT_NAME = "Адыгея";
	public static final String SUBJECT_TYPE = "Респ";
	public static final String REGION_NAME = "Майкоп";
	public static final String REGION_TYPE = "г";
	public static final String LOCALITY_NAME = "Веселый";
	public static final String LOCALITY_TYPE = "х";
	public static final String STREET_NAME = "Веселая";
	public static final String STREET_TYPE = "ул";

	public static final String SUBJECT_CODE = "0100000000000";
	public static final String REGION_CODE = "0100000100000";
	public static final String LOCALITY_CODE = "0100000100200";
	public static final String STREET_CODE = "01000001002000900";

	@Autowired
	private KLADRLocalityDao dao;

	@Test
	@Transactional
	public void testFindSubject() {
		List<KLADRLocality> list = dao.findSubject(SUBJECT_NAME, SUBJECT_TYPE);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(SUBJECT_CODE, list.get(0).getCode());
	}

	@Test
	@Transactional
	public void testFindRegion() {
		List<KLADRLocality> list = dao.findRegion(SUBJECT_CODE, REGION_NAME, REGION_TYPE);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(REGION_CODE, list.get(0).getCode());
	}

	@Test
	@Transactional
	public void testFindLocality() {
		List<KLADRLocality> list = dao.findLocality(REGION_CODE, LOCALITY_NAME, LOCALITY_TYPE);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(LOCALITY_CODE, list.get(0).getCode());
	}

}
