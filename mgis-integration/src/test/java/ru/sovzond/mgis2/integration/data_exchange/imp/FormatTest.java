package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static ru.sovzond.mgis2.integration.data_exchange.imp.LandsImporter.YYYY_MM_DD;

/**
 * Created by Alexander Arakelyan on 20.11.15.
 */
public class FormatTest {

	@Test
	public void testDate() throws ParseException {
		Date date = new SimpleDateFormat(YYYY_MM_DD).parse("2001-04-20");
		Assert.assertNotNull(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Assert.assertEquals(2001, cal.get(Calendar.YEAR));
		Assert.assertEquals(4, cal.get(Calendar.MONTH) + 1);
		Assert.assertEquals(20, cal.get(Calendar.DATE));
	}
}
