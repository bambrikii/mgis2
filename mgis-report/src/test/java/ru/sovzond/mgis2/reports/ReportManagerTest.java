package ru.sovzond.mgis2.reports;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Alexander Arakelyan on 12/12/15.
 */
public class ReportManagerTest {
	@Test
	public void testGenerateReport() throws IOException, ReportManagerException {
		byte[] result = new ReportManager().generateReport("{ \"a\": \"a1\"}", ReportManagerTest.class.getResourceAsStream("Blank_A4.jasper"), ReportOutputFormat.PDF);
		Assert.assertNotNull(result);
	}
}
