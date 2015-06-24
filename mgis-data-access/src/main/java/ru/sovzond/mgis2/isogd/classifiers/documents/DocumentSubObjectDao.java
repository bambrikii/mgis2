package ru.sovzond.mgis2.isogd.classifiers.documents;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableCRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableFilter;
import ru.sovzond.mgis2.isogd.Volume;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Repository
public class DocumentSubObjectDao extends PageableCRUDDaoBase<DocumentSubObject> {

    private class DocumentSubObjectByVolumeFilter extends PageableFilter<DocumentSubObject> {

        public DocumentSubObjectByVolumeFilter(Volume volume) {
            // add alias .
            addCriterion(Restrictions.eq("volume", volume));
        }
    }

    public DocumentSubObjectByVolumeFilter createFilter(Volume volume) {
        return new DocumentSubObjectByVolumeFilter(volume);
    }

    private class DocumentSubObjectFilterBuilder extends PageableFilter<DocumentSubObject> {
        public DocumentSubObjectFilterBuilder(DocumentObject documentObject) {
            addCriterion(Restrictions.eq("documentObject", documentObject));
        }
    }

    public PageableFilter<DocumentSubObject> createFilter(DocumentObject documentObject) {
        return new DocumentSubObjectFilterBuilder(documentObject);
    }
}
