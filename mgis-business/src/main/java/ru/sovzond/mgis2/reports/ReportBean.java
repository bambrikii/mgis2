package ru.sovzond.mgis2.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 11/12/15.
 */
@Service
public class ReportBean extends CRUDBeanBase<Report> {

	@Autowired
	private ReportDao dao;

	private ReportManager reportManager = new ReportManager();

	@Override
	protected IPageableDAOBase<Report> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Report> getIIdentifiableDao() {
		return dao;
	}

	public byte[] generate(Report report, ReportOutputFormat outputFormat, String json) throws ReportManagerException {
		return reportManager.generateReport(json, report.getBytes(), outputFormat);
	}

	public PageableContainer<Report> list(String filter, String orderBy, int first, int max) {
		Pageable<Report> pager = dao.pager(dao.createFilter(filter, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(report -> report.clone()).collect(Collectors.toList()), pager.count(), first, max);
	}
}
