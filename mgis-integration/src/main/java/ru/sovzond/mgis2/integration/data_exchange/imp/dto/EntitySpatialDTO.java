package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
public class EntitySpatialDTO {
	private String entSys;
	private SpatialElementDTO[] spatialElements;

	public String getEntSys() {
		return entSys;
	}

	public void setEntSys(String entSys) {
		this.entSys = entSys;
	}

	public SpatialElementDTO[] getSpatialElements() {
		return spatialElements;
	}

	public void setSpatialElements(SpatialElementDTO[] spatialElements) {
		this.spatialElements = spatialElements;
	}
}
