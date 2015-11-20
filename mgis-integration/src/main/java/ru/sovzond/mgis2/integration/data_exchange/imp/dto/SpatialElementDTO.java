package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */

public class SpatialElementDTO {

	public SpatialElementUnitDTO[] spatialElementUnits;

	public SpatialElementUnitDTO[] getSpatialElementUnits() {
		return spatialElementUnits;
	}

	public void setSpatialElementUnits(SpatialElementUnitDTO[] spatialElementUnits) {
		this.spatialElementUnits = spatialElementUnits;
	}
}
