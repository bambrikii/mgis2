package ru.sovzond.mgis2.isogd;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.sovzond.mgis2.dataaccess.base.HibernateConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class DocumentDaoTest {

	@Autowired
	private SectionDao sectionDao;

	@Autowired
	private VolumeDao volumeDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private DocumentDao documentDao;

	@Test
	@Transactional
	public void testDocument() {
		Section section = new Section();
		sectionDao.persist(section);
		Assert.assertTrue(section.getId() != null);

		try {
			Volume volume = new Volume();
			volumeDao.persist(volume);
			Assert.assertTrue(volume.getId() != null);

			try {
				Book book = new Book();
				bookDao.persist(book);
				Assert.assertTrue(book.getId() != null);

				try {
					Document document = new Document();
					documentDao.persist(document);

					Assert.assertTrue(section.getId() != null);
					documentDao.delete(document);
				} finally {
					bookDao.delete(book);
				}
			} finally {
				volumeDao.delete(volume);
			}
		} finally {
			sectionDao.delete(section);
		}
	}
}
