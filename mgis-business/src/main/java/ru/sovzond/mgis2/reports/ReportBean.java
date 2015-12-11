package ru.sovzond.mgis2.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

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
}
