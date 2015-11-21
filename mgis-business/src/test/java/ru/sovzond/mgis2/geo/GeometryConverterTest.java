package ru.sovzond.mgis2.geo;

import com.vividsolutions.jts.geom.MultiPolygon;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
public class GeometryConverterTest {

	private static final String MSK50ZONE2 = "+proj=tmerc +lat_0=0 +lon_0=38.48333333333 +k=1 +x_0=2250000 +y_0=-5712900.566 +ellps=krass +towgs84=-118.754,-61.782,-93.237,2.40896,3.47502,-1.29688,-6.5177 +units=m +no_defs";

	@Test
	public void testConvert1() {
		SpatialGroup geometry = new SpatialGroup();
		CoordinateSystem coordinateSystem = new CoordinateSystem();
		coordinateSystem.setConversionRules(MSK50ZONE2);
		geometry.setCoordinateSystem(coordinateSystem);

		SpatialElement element1 = new SpatialElement();
		element1.getCoordinates().add(createCoordinates(0, 0));
		element1.getCoordinates().add(createCoordinates(5, 0));
		element1.getCoordinates().add(createCoordinates(2.5, 3));

		SpatialElement element2 = new SpatialElement();
		element2.getCoordinates().add(createCoordinates(10, 10));
		element2.getCoordinates().add(createCoordinates(10, 20));
		element2.getCoordinates().add(createCoordinates(20, 20));
		element2.getCoordinates().add(createCoordinates(20, 10));

		geometry.getSpatialElements().add(element1);
		geometry.getSpatialElements().add(element2);
		GeometryConverter converter = new GeometryConverter(coordinateSystem);
		MultiPolygon converted = converter.convert(geometry.getSpatialElements());
		Assert.assertNotNull(converted);
		//		Assert.assertEquals(2, converted.getEnvelope().getNumGeometries());
		System.out.println(converted);
		System.out.println(converted.getEnvelope());
	}

	private Coordinate createCoordinates(double x, double y) {
		Coordinate coordinate = new Coordinate();
		coordinate.setX(new BigDecimal(x));
		coordinate.setY(new BigDecimal(y));
		return coordinate;
	}
}
