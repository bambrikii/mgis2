package ru.sovzond.mgis2.integration.data_exchange.imp;

import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportRecord;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
public interface Importable {
	List<ReportRecord> imp(File file);

	List<ReportRecord> imp(InputStream inputStream);
}
