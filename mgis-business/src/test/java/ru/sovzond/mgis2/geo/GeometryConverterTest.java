package ru.sovzond.mgis2.geo;

import com.vividsolutions.jts.geom.MultiPolygon;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
public class GeometryConverterTest {

	private static final String MSK23ZONE1 = "PROJCS[\"MSK23_zona1\"" +
			",GEOGCS[\"GCS_Pulkovo_1942\"" +
			",DATUM[\"D_Pulkovo_1942\"" +
			",SPHEROID[\"Krasovsky_1940\",6378245.0,298.3]" +
			"]" +
			",PRIMEM[\"Greenwich\",0.0]" +
			",UNIT[\"Degree\",0.0174532925199433]" +
//			",METADATA[\"Europe - FSU onshore\",19.58,35.15,-168.98,81.9,0.0,0.0174532925199433,0.0,2423]" +
			"]" +
			",PROJECTION[\"Transverse_Mercator\"]" +
			",PARAMETER[\"False_Easting\",1300000.0]" +
			",PARAMETER[\"False_Northing\",-4511057.628]" +
			",PARAMETER[\"Central_Meridian\",37.98333333333]" +
			",PARAMETER[\"Scale_Factor\",1.0]" +
			",PARAMETER[\"Latitude_Of_Origin\",0.0]" +
			",UNIT[\"Meter\",1.0]" +
			"]";

	@Test
	public void testConvert1() {
		SpatialGroup geometry = new SpatialGroup();
		CoordinateSystem coordinateSystem = new CoordinateSystem();
		coordinateSystem.setConversionRules(MSK23ZONE1);
		geometry.setCoordinateSystem(coordinateSystem);

		SpatialElement element1 = new SpatialElement();
		element1.getCoordinates().add(createCoordinates(369089.90, 1391649.29));
		element1.getCoordinates().add(createCoordinates(369087.77, 1391647.03));
		element1.getCoordinates().add(createCoordinates(369097.02, 1391636.94));
		element1.getCoordinates().add(createCoordinates(369099.39, 1391630.54));
		element1.getCoordinates().add(createCoordinates(369103.36, 1391627.50));
		element1.getCoordinates().add(createCoordinates(369099.64, 1391622.63));
		element1.getCoordinates().add(createCoordinates(369106.95, 1391615.97));
		element1.getCoordinates().add(createCoordinates(369108.80, 1391614.29));
		element1.getCoordinates().add(createCoordinates(369113.38, 1391616.85));
		element1.getCoordinates().add(createCoordinates(369120.58, 1391621.32));
		element1.getCoordinates().add(createCoordinates(369121.69, 1391625.34));
		element1.getCoordinates().add(createCoordinates(369115.18, 1391632.93));
		element1.getCoordinates().add(createCoordinates(369089.90, 1391649.29));

		SpatialElement element2 = new SpatialElement();
		element2.getCoordinates().add(createCoordinates(10, 10));
		element2.getCoordinates().add(createCoordinates(10, 20));
		element2.getCoordinates().add(createCoordinates(20, 20));
		element2.getCoordinates().add(createCoordinates(20, 10));
		element2.getCoordinates().add(createCoordinates(10, 10));

		geometry.getSpatialElements().add(element1);
		geometry.getSpatialElements().add(element2);
		GeometryConverter converter = new GeometryConverter(coordinateSystem.getConversionRules());
		MultiPolygon converted = converter.convert(converter.createMultipolygon(geometry.getSpatialElements()));
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
