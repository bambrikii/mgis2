package ru.sovzond.mgis2.reports;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JsonDataSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander Arakelyan on 12/12/15.
 */
public class ReportManager {
	public byte[] generateReport(String json, byte[] reportBytes, ReportOutputFormat outputFormat) throws ReportManagerException {
		try (InputStream inputStream = new ByteArrayInputStream(reportBytes)) {
			return generateReport(json, inputStream, outputFormat);
		} catch (IOException ex) {
			throw new ReportManagerException(ex.getMessage(), ex);
		}
	}

	public byte[] generateReport(String json, InputStream reportInputStream, ReportOutputFormat outputFormat) throws IOException, ReportManagerException {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Map<String, Object> parameters = new HashMap<>();
			try (ByteArrayInputStream jsonStream = new ByteArrayInputStream(json.getBytes())) {
				JRDataSource dataSource = new JsonDataSource(jsonStream);
				JasperFillManager.fillReportToStream(reportInputStream, outputStream, parameters, dataSource);
				try (ByteArrayInputStream is = new ByteArrayInputStream(outputStream.toByteArray())) {
					try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
						switch (outputFormat) {
							case PDF:
								JasperExportManager.exportReportToPdfStream(is, os);
								break;
							case XML:
								JasperExportManager.exportReportToXmlStream(is, os);
								break;
							default:
								throw new ReportManagerException("OUTPUT_FORMAT_NOT_SUPPORTED");
						}
						return os.toByteArray();
					} catch (JRException ex) {
						throw new ReportManagerException(ex.getMessage(), ex);
					}
				}
			} catch (JRException ex) {
				throw new ReportManagerException(ex.getMessage(), ex);
			}
		}
	}
}
