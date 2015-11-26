package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
public class EntitySpatialDTO {

	public String entSys;
	public List<SpatialElementDTO> spatialElements;

	public String getEntSys() {
		return entSys;
	}

	public void setEntSys(String entSys) {
		this.entSys = entSys;
	}

	public List<SpatialElementDTO> getSpatialElements() {
		return spatialElements;
	}

	public void setSpatialElements(List<SpatialElementDTO> spatialElements) {
		this.spatialElements = spatialElements;
	}


}
