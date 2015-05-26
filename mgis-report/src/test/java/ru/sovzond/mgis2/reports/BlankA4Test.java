package ru.sovzond.mgis2.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.junit.Test;

public class BlankA4Test {

	@Test
	public void test2() throws IOException, JRException {
		try (InputStream inputStream = BlankA4Test.class.getResourceAsStream("/Blank_A4.jasper")) {
			try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				Collection<SampleClass1> dataList = new ArrayList<SampleClass1>();
				SampleClass1 sample1 = new SampleClass1(1, "name 1", "comment 1");
				sample1.getList().add(new SampleClass2("str1-1"));
				sample1.getList().add(new SampleClass2("str1-2"));
				sample1.getList().add(new SampleClass2("str1-3"));
				dataList.add(sample1);
				SampleClass1 sample2 = new SampleClass1(2, "name 2", "comment 2");
				sample2.getList().add(new SampleClass2("str2-1"));
				dataList.add(sample2);
				SampleClass1 sample3 = new SampleClass1(3, "name 3", "comment 3");
				sample3.getList().add(new SampleClass2("str3-1"));
				dataList.add(sample3);
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
				JasperFillManager.fillReportToStream(inputStream, outputStream, parameters, beanColDataSource);
				try (ByteArrayInputStream is = new ByteArrayInputStream(outputStream.toByteArray())) {
					try (OutputStream os = new FileOutputStream(new File("Blank_A4.pdf"))) {
						JasperExportManager.exportReportToPdfStream(is, os);
					}
				}
			}
		}
	}
}
