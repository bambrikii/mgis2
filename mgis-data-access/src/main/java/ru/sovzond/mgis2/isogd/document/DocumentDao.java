package ru.sovzond.mgis2.isogd.document;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableBase;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;

import java.util.List;

@Repository
public class DocumentDao extends PageableDAOBase<Document> {
    public Document findById(Long id) {
        return (Document) filter(Restrictions.eq("id", id)).uniqueResult();
    }

    public List<DocumentSubObject> listAvailableDocumentSubObjects(Volume volume) {
        //        return volume.getBook().getDocumentObject().getDocumentSubObjects();
        return getSession() //
                .createQuery("SELECT docSubObj FROM Volume vol JOIN vol.book book JOIN book.documentObject docObj JOIN docObj.documentSubObjects docSubObj WHERE vol = :vol ") //
                .setParameter("vol", volume) //
                .list();
    }

    public DocumentClass readDocumentClassByVolume(Volume volume) {
        return (DocumentClass) getSession() //
                .createQuery("SELECT docClass FROM Volume vol JOIN vol.book book JOIN book.section sect JOIN sect.documentClass docClass WHERE vol = :vol") //
                .setParameter("vol", volume) //
                .uniqueResult();
    }

    class DocumentBaseBuilder extends PageableBase<Document> {
        private Volume volume;

        private DocumentBaseBuilder(Volume volume, String orderBy, int first, int max) {
            this.volume = volume;
            this.orderBy = prepareOrderBy(orderBy);
            this.first = first;
            this.max = max;
        }

        @Override
        protected void applyFilter(Criteria criteria) {
            criteria.add(Restrictions.eq("volume", volume));
        }
    }

    public PageableBase<Document> createFilter(Volume volume, String order, int first, int max) {
        return new DocumentBaseBuilder(volume, order, first, max);
    }
}
