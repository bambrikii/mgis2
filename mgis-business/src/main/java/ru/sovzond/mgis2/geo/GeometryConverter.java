package ru.sovzond.mgis2.geo;

import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import org.hibernatespatial.cfg.GeometryFactoryHelper;
import org.hibernatespatial.mgeom.MGeometryFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 22.10.15.
 */
public class GeometryConverter {

	private CoordinatesConverter coordinatesConverter;
	private MGeometryFactory geometryFactory;

	public GeometryConverter(CoordinateSystem coordinateSystem) {
		coordinatesConverter = new CoordinatesConverter(coordinateSystem.getConversionRules());
		geometryFactory = GeometryFactoryHelper.createGeometryFactory(null);
	}

	public MultiPolygon convert(List<SpatialElement> elements) {
		List<Polygon> polygons = new ArrayList<>();
		if (elements.size() > 0) {
			CoordinateSequence coordinateSequence = buildCoordinateSequence(elements.get(0));
			LinearRing shell = new LinearRing(coordinateSequence, geometryFactory);
			List<LinearRing> holes = new ArrayList<>();
			for (int i = 1; i < elements.size(); i++) {
				coordinateSequence = buildCoordinateSequence(elements.get(i));
				LinearRing hole = new LinearRing(coordinateSequence, geometryFactory);
				holes.add(hole);
			}
			polygons.add(new Polygon(shell, holes.toArray(new LinearRing[holes.size()]), geometryFactory));
		}
		return new MultiPolygon(polygons.toArray(new Polygon[polygons.size()]), geometryFactory);
	}

	private CoordinateSequence buildCoordinateSequence(SpatialElement element) {
		List<com.vividsolutions.jts.geom.Coordinate> coordinates = new ArrayList<>();
		for (int i = 0; i < element.getCoordinates().size(); i++) {
			Coordinate coordinate = element.getCoordinates().get(i);
			double[] converted = coordinatesConverter.convert(coordinate.getX().doubleValue(), coordinate.getY().doubleValue());
			coordinates.add(new com.vividsolutions.jts.geom.Coordinate(converted[0], converted[1]));
		}
		return new CoordinateArraySequence(coordinates.toArray(new com.vividsolutions.jts.geom.Coordinate[coordinates.size()]));
	}
}
