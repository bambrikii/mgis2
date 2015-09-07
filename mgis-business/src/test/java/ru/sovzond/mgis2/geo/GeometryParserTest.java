package ru.sovzond.mgis2.geo;

import com.vividsolutions.jts.geom.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alexander Arakelyan on 07.09.15.
 */
public class GeometryParserTest {
	@Test
	public void testWktToGeometry_point() {
		testGeometry("POINT(6 10)", Point.class);
	}

	@Test
	public void testWktToGeometry_multiPoint() {
		testGeometry("MULTIPOINT(3.5 5.6, 4.8 10.5)", MultiPoint.class);
	}

	@Test
	public void testWktToGeometry_line() {
		testGeometry("LINESTRING(3 4,10 50,20 25)", LineString.class);
	}

	@Test
	public void testWktToGeometry_multiLine() {
		testGeometry("MULTILINESTRING((3 4,10 50,20 25),(-5 -8,-10 -8,-15 -4))", MultiLineString.class);
	}

	@Test
	public void testWktToGeometry_polygon() {
		testGeometry("POLYGON((1 1,5 1,5 5,1 5,1 1),(2 2, 3 2, 3 3, 2 3,2 2))", Polygon.class);
	}

	@Test
	public void testWktToGeometry_multipolygon() {
		testGeometry("MULTIPOLYGON(((1 1,5 1,5 5,1 5,1 1),(2 2, 3 2, 3 3, 2 3,2 2)),((3 3,6 2,6 4,3 3)))", MultiPolygon.class);
	}

	@Test
	public void testWktToGeometry_geometryCollection() {
		testGeometry("GEOMETRYCOLLECTION(POINT(4 6),LINESTRING(4 6,7 10))", GeometryCollection.class);
	}

	private void testGeometry(String wktString, Class<?> cls) {
		Geometry geometry = GeometryParser.wktToGeometry(wktString);
		Assert.assertNotNull(geometry);
		Assert.assertEquals(cls, geometry.getClass());
	}

}
