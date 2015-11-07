package ru.sovzond.mgis2.geo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alexander Arakelyan on 23.10.15.
 */
public class CoordinatesConverterTest {
	@Test
	public void testParamsConversion() {
		CoordinatesConverter converter = new CoordinatesConverter("+proj=1 +to=2 +towgs");
		String[] params = converter.getParams();
		Assert.assertTrue(params.length == 3);
		Assert.assertEquals("+proj=1", params[0]);
		Assert.assertEquals("+to=2", params[1]);
		Assert.assertEquals("+towgs", params[2]);
	}

	@Test
	public void testConvertAustria() {
		String rules = "+proj=tmerc +lat_0=0 +lon_0=10d20 +k=1 +x_0=150000 +y_0=-5000000 +ellps=bessel";
		CoordinatesConverter converter = new CoordinatesConverter(rules);
		print(converter.convert(1, 1));
	}

	private void print(double[] arr) {
		for (double item : arr) {
			System.out.println(item);
		}
	}

	@Test
	public void testConversionZero() {
		String rules = "+proj=tmerc lat_0=0 lon_0=0 +ellps +k=1 +towgs84=0,0,0,0,0,0,0 +units=m +x=0 +y=0 +alpha=1 +no_defs +to +datum=WGS84";
		CoordinatesConverter converter = new CoordinatesConverter(rules);
		print(converter.convert(1, 1));
	}

	@Test
	public void testConversion() {
		String rules = "+proj=tmerc lat_0=0 lon_0=50.553 +ellps=krass +k=1 +towgs84=0,0,0,0,0,0,0 +units=m +x=2250000 +y=-5914743.504 +to +datum=WGS84";
		CoordinatesConverter converter = new CoordinatesConverter(rules);
		print(converter.convert(0, 0));
	}

	@Test
	public void testTransverseMercator() {
		double[] convert = new CoordinatesConverter("+proj=tmerc lat_0=45 lon_0=10").convert(0, 0);
		Assert.assertEquals(2, convert.length);
		print(convert);
	}

	@Test
	public void testPoly() {
		print(new CoordinatesConverter("+proj=poly lat_0=45 lon_0=10").convert(786491.58, 5033320.60));
	}
}
