package ru.sovzond.mgis2.integration.data_exchange.imp.report;

/**
 * Created by Alexander Arakelyan on 30/11/15.
 */
public class ReportRecord {
	private String identifier;
	private String message;
	private String details;
	private ReportOutcome outcome;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public ReportOutcome getOutcome() {
		return outcome;
	}

	public void setOutcome(ReportOutcome outcome) {
		this.outcome = outcome;
	}
}
