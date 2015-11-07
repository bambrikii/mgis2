package ru.sovzond.mgis2.geo;

import com.jhlabs.map.Point2D;
import com.jhlabs.map.proj.Projection;
import com.jhlabs.map.proj.ProjectionFactory;
import org.junit.Test;

/**
 * Created by Alexander Arakelyan on 22.10.15.
 */
public class Proj4JTest {
	@Test
	public void test1() {
		String[] params = {"proj=tmerc", "lat_0=37.5", "lon_0=-85.66666666666667", "k=0.999966667", "x_0=99999.99989839978", "y_0=249999.9998983998", "ellps=GRS80", "datum=NAD83", "to_meter=0.3048006096012192", "no_defs"};
		Projection proj = ProjectionFactory.fromPROJ4Specification(params);
		Point2D.Double d1 = new Point2D.Double(1, 1);
		Point2D.Double d2 = new Point2D.Double(1, 1);
		Point2D.Double result = proj.transform(d1, d2);
		System.out.println("coordinates: " + result.x + ", " + result.y);
	}

	@Test
	public void test2() {
		Projection projection = ProjectionFactory.fromPROJ4Specification(new String[]{"+proj=tmerc", "+zone=33", "+ellps=GRS80", "+towgs84=0,0,0,0,0,0,0", "+units=m", "+no_defs"});
		Point2D.Double d1 = new Point2D.Double(1, 1);
		Point2D.Double d2 = new Point2D.Double(1, 1);
		Point2D.Double result = projection.transform(d1, d2);
		System.out.println("coordinates: " + result.x + ", " + result.y);
	}

	@Test
	public void test3() {
		// "МСК-23 зона 1", 8, 1001, 7, 37.98333333333, 0, 1, 1300000, -4511057.628
		// "МСК-23 зона 2", 8, 1001, 7, 40.98333333333, 0, 1, 2300000, -4511057.628
		Projection projection = ProjectionFactory.fromPROJ4Specification(new String[]{"+proj=tmerc", "lat_0=0", "lon_0=37.98333333333",
				//"+ellps",
				//"+k=1",
				"+towgs84=0,0,0,0,0,0,0", "+units=m", "+x=1300000", "+y=-4511057.628",
				//"+alpha=1",
				//"+no_defs"
				"+to",
				//"+proj=latlong",
				"+datum=WGS84"});
		Point2D.Double d1 = new Point2D.Double(466899.37, 1373817.13);
		Point2D.Double d2 = new Point2D.Double(1, 1);
		Point2D.Double result = projection.transform(d1, d2);
		System.out.println("coordinates: " + d2.x + ", " + d2.y);
	}

	@Test
	public void testMSKZone2() {
		// "МСК-23 зона 2", 8, 1001, 7, 40.98333333333, 0, 1, 2300000, -4511057.628
		Projection projection = ProjectionFactory.fromPROJ4Specification(new String[]{"+proj=tmerc", "+lat_0=0", "+lon_0=38.48333333333", "+k=1", "+x_0=2250000", "+y_0=-5712900.566", "+ellps=krass", "+towgs84=-118.754,-61.782,-93.237,2.40896,3.47502,-1.29688,-6.5177", "+units=m", "+no_defs"});
		Point2D.Double d1 = new Point2D.Double(466899.37, 1373817.13);
		Point2D.Double d2 = new Point2D.Double(1, 1);
		Point2D.Double result = projection.transform(d1, d2);
		System.out.println("coordinates: " + d2.x + ", " + d2.y);
	}
}
