package ru.sovzond.mgis2.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alexander Arakelyan on 05.10.15.
 */
@Service
public class SpatialDataBean {

	@Autowired
	private CoordinateSystemBean coordinateSystemBean;


	@Autowired
	private SpatialGroupBean spatialGroupBean;

	public SpatialGroup save(SpatialGroup source, SpatialGroup persistent) {
		if (persistent == null || persistent.getId() == null || persistent.getId() == 0) {
			persistent = new SpatialGroup();
		}
		CoordinateSystem sourceCoordinateSystem = source.getCoordinateSystem();
		if (sourceCoordinateSystem == null || sourceCoordinateSystem.getId() == null || sourceCoordinateSystem.getId() == 0) {
			throw new IllegalArgumentException("GEO_COORDINATE_SYSTEM_PROPERTY_REQUIRED");
		}
		persistent.setCoordinateSystem(coordinateSystemBean.load(sourceCoordinateSystem.getId()));
		persistent.getSpatialElements().clear();
		for (SpatialElement spatialElement : source.getSpatialElements()) {
			SpatialElement spatialElement2 = new SpatialElement();
			spatialElement2.setPosition(spatialElement.getPosition());
			for (Coordinate coordinate : spatialElement.getCoordinates()) {
				Coordinate coordinate2 = new Coordinate();
				coordinate2.setPosition(coordinate.getPosition());
				coordinate2.setX(coordinate.getX());
				coordinate2.setY(coordinate.getY());
				spatialElement2.getCoordinates().add(coordinate2);
			}
			persistent.getSpatialElements().add(spatialElement2);
		}
		spatialGroupBean.save(persistent);
		return persistent;
	}

}
