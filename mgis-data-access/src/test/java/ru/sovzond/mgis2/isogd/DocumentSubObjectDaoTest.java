package ru.sovzond.mgis2.isogd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sovzond.mgis2.dataaccess.base.HibernateConfiguration;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;
import ru.sovzond.mgis2.isogd.document.DocumentDao;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 25.06.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class DocumentSubObjectDaoTest {

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private BookDao bookDao;


    @Autowired
    private VolumeDao volumeDao;

    @Autowired
    private DocumentDao documentDao;

    @Test
    @Transactional
    public void testVolumeFilter() {
        Section section = null;
        try {
            section = new Section();
            section.setName("testSection1");
            sectionDao.save(section);
            Book book = null;
            try {
                book = new Book();
                book.setName("testBook1");
                book.setSection(section);
                bookDao.save(book);
                Volume volume = null;
                try {
                    volume = new Volume();
                    volume.setName("testVolume1");
                    volume.setBook(book);
                    volumeDao.save(volume);
                    List<DocumentSubObject> listAvailableDocumentSubObjects = documentDao.listAvailableDocumentSubObjects(volume);
                    Assert.assertNotNull(listAvailableDocumentSubObjects);
                } finally {
                    if (volume != null && volume.getId() > 0) {
                        volumeDao.delete(volume);
                    }
                }
            } finally {
                if (book != null && book.getId() > 0) {
                    bookDao.delete(book);
                }
            }
        } finally {
            if (section != null && section.getId() > 0) {
                sectionDao.delete(section);
            }
        }
    }
}
