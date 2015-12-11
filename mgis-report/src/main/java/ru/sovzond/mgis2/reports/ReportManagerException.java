package ru.sovzond.mgis2.reports;

/**
 * Created by Alexander Arakelyan on 12/12/15.
 */
public class ReportManagerException extends Exception {
	public ReportManagerException(String message, Exception throwable) {
		super(message, throwable);
	}

	public ReportManagerException(String message) {
		super(message);
	}
}
