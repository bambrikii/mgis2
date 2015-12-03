package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */

public class SpatialElementDTO {

	public List<SpatialElementUnitDTO> spatialElementUnits;

	public List<SpatialElementUnitDTO> getSpatialElementUnits() {
		return spatialElementUnits;
	}

	public void setSpatialElementUnits(List<SpatialElementUnitDTO> spatialElementUnits) {
		this.spatialElementUnits = spatialElementUnits;
	}
}
