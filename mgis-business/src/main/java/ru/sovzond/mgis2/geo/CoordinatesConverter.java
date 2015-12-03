package ru.sovzond.mgis2.geo;

import com.vividsolutions.jts.geom.Geometry;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
public class CoordinatesConverter {
	private MathTransform transform;

	public CoordinatesConverter(String params) {
		if (params == null || params.length() == 0) {
			throw new IllegalArgumentException("CS Transformation rules required.");
		}
		try {
			CoordinateReferenceSystem sourceCRS = CRS.parseWKT(params);
//			String wgs84 = "GEOGCS[\"WGS 84\",DATUM[\"WGS_1984\",SPHEROID[\"WGS 84\",6378137,298.257223563,AUTHORITY[\"EPSG\",\"7030\"]],AUTHORITY[\"EPSG\",\"6326\"]],PRIMEM[\"Greenwich\",0,AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\",0.0174532925199433,AUTHORITY[\"EPSG\",\"9122\"]],AUTHORITY[\"EPSG\",\"4326\"]]";
//			CRS.parseWKT(wgs84);
			CoordinateReferenceSystem targetCRS = DefaultGeographicCRS.WGS84;

			transform = CRS.findMathTransform(sourceCRS, targetCRS, true);
		} catch (FactoryException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public Geometry convert(Geometry sourceGeometry) {
		try {
			Geometry targetGeometry = JTS.transform(sourceGeometry, transform);
			return targetGeometry;
		} catch (TransformException ex) {
			throw new IllegalArgumentException(ex);
		}
	}
}
