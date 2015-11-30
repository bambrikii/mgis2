package ru.sovzond.mgis2.integration.data_exchange.imp.report;

import ru.sovzond.mgis2.common.exceptions.StackTraceFactory;

/**
 * Created by Alexander Arakelyan on 30.11.15.
 */
public class ReportFactory {

	public static ReportRecord success(String identifier) {
		ReportRecord report = new ReportRecord();
		report.setIdentifier(identifier);
		report.setMessage("OK");
		report.setOutcome(ReportOutcome.SUCCESS);
		return report;
	}

	public static ReportRecord error(String identifier, Exception ex) {
		ReportRecord report = new ReportRecord();
		report.setIdentifier(identifier);
		report.setMessage(ex.getMessage());
		report.setDetails(StackTraceFactory.stackTraceToString(ex));
		report.setOutcome(ReportOutcome.ERROR);
		return report;
	}
}
