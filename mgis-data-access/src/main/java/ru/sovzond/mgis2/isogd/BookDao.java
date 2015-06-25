package ru.sovzond.mgis2.isogd;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sovzond.mgis2.dataaccess.base.impl.PageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableFilter;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;

import java.util.List;

@Repository
public class BookDao extends PageableDAOBase<Book> {
    public Book findById(Long id) {
        return (Book) filter(Restrictions.eq("id", id)).uniqueResult();
    }

    public BookFilterBuilder createFilter(Section section) {
        return new BookFilterBuilder(section);
    }

    public List<DocumentObject> listAvailableDocumentObjects(Section section) {
        return getSession().createQuery("SELECT docObj FROM Section s JOIN s.documentClass docCls JOIN docCls.documentObjects docObj WHERE s = :sect ") //
                .setParameter("sect", section) //
                .list();
    }

    class BookFilterBuilder extends PageableFilter<Book> {
        private Section section;

        private BookFilterBuilder(Section section) {
            this.section = section;
        }

        @Override
        protected void apply(Criteria criteria) {
            criteria.add(Restrictions.eq("section", section));
        }
    }
}
