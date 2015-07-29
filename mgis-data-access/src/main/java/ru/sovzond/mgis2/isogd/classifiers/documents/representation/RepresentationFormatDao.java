package ru.sovzond.mgis2.isogd.classifiers.documents.representation;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Repository
public class RepresentationFormatDao extends CRUDDaoBase<RepresentationFormat> {
	public List<RepresentationFormat> findByFormat(String contentType) {
		return getSession().createQuery("SELECT reprFmt FROM RepresentationFormat reprFmt JOIN reprFmt.formats f WHERE f IN (:fmts) ").setParameter("fmts", contentType).list();
	}
}
