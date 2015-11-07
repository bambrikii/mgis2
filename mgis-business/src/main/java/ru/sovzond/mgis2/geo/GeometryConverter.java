package ru.sovzond.mgis2.geo;

//import com.jhlabs.map.Point2D;
//import com.jhlabs.map.proj.Projection;
//import com.jhlabs.map.proj.ProjectionFactory;

/**
 * Created by Alexander Arakelyan on 22.10.15.
 */
public class GeometryConverter {
	private String[] params;

	public GeometryConverter(String params) {
		if (params == null) {
			throw new IllegalArgumentException("Params argument required.");
		}
		this.params = params.trim().split("\\s+");
	}

	public String[] getParams() {
		return params.clone();
	}

	//	public double[] convert(double x, double y) {
	//		Projection projection = ProjectionFactory.fromPROJ4Specification(params);
	//		Point2D.Double d1 = new Point2D.Double(x, y);
	//		Point2D.Double d2 = new Point2D.Double(0, 0);
	//		projection.transform(d1, d2);
	//		return new double[]{d2.x, d2.y};
	//	}
}
