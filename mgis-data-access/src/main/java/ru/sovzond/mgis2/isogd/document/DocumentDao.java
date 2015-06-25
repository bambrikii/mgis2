package ru.sovzond.mgis2.isogd.document;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableFilter;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;

import java.util.List;

@Repository
public class DocumentDao extends PageableDAOBase<Document> {
    public Document findById(Long id) {
        return (Document) filter(Restrictions.eq("id", id)).uniqueResult();
    }

    public List<DocumentSubObject> listAvailableDocumentSubObjects(Volume volume) {
//        return volume.getBook().getDocumentObject().getDocumentSubObjects();
        return getSession().createQuery("SELECT docSubObj FROM Volume vol JOIN vol.book book JOIN book.documentObject docObj JOIN docObj.documentSubObjects docSubObj WHERE vol = :vol ") //
                .setParameter("vol", volume) //
                .list();
    }

    class DocumentFilterBuilder extends PageableFilter<Document> {
        private Volume volume;

        private DocumentFilterBuilder(Volume volume) {
            this.volume = volume;
        }

        @Override
        protected void apply(Criteria criteria) {
            criteria.add(Restrictions.eq("volume", volume));
        }
    }

    public PageableFilter<Document> createFilter(Volume volume) {
        return new DocumentFilterBuilder(volume);
    }
}
