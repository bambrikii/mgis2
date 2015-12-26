package ru.sovzond.mgis2.integration.data_exchange.imp.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */
public class ReportCollector {
	private List<ReportRecord> reports = new ArrayList<>();

	public void report(String message) {
		reports.add(ReportFactory.success(message));
	}

	public void report(String message, Exception ex) {
		reports.add(ReportFactory.error(message, ex));
	}

	public List<ReportRecord> getReports() {
		return reports;
	}
}
