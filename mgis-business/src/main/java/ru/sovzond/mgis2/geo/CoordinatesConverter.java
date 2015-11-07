package ru.sovzond.mgis2.geo;

import com.jhlabs.map.Point2D;
import com.jhlabs.map.proj.Projection;
import com.jhlabs.map.proj.ProjectionFactory;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
public class CoordinatesConverter {
	private String[] params;

	public CoordinatesConverter(String params) {
		if (params == null) {
			throw new IllegalArgumentException("Params argument required.");
		}
		this.params = buildConversionParams(params);
	}

	private String[] buildConversionParams(String params) {
		return params.trim().split("\\s+");
	}

	public String[] getParams() {
		return params.clone();
	}

	public double[] convert(double x, double y) {
		Projection projection = ProjectionFactory.fromPROJ4Specification(params);
		Point2D.Double d1 = new Point2D.Double(x, y);
		Point2D.Double d2 = new Point2D.Double(0, 0);
		projection.transform(d1, d2);
		return new double[]{d2.x, d2.y};
	}
}
