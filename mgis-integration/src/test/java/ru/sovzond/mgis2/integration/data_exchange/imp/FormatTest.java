package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.sovzond.mgis2.integration.data_exchange.imp.LandImportResolverBean.CADASTRAL_BLOCK_PATTERN;
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

	@Test
	public void testCadastralBlockPattern() {
		Pattern cadastralNumberPattern = Pattern.compile(CADASTRAL_BLOCK_PATTERN);
		String landCadastralNumber = "72:17:0201005:2";
		Matcher matcher = cadastralNumberPattern.matcher(landCadastralNumber);
		Assert.assertTrue(matcher.matches());
		Assert.assertEquals("72", matcher.group(1));
		Assert.assertEquals("17", matcher.group(2));
		Assert.assertEquals("02", matcher.group(3));
		Assert.assertEquals("01", matcher.group(4));
		Assert.assertEquals("005", matcher.group(5));
	}

	@Test
	public void testDouble() {
		String str = "325170.46";
		Double result = Double.parseDouble(str);
		Assert.assertEquals(Double.valueOf(325170.46), result);
	}
}
