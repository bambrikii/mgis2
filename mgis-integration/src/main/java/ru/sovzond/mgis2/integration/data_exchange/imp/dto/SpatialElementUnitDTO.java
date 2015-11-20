package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
public class SpatialElementUnitDTO {

	public OrdinateDTO[] ordinates;

	public OrdinateDTO[] getOrdinates() {
		return ordinates;
	}

	public void setOrdinates(OrdinateDTO[] ordinates) {
		this.ordinates = ordinates;
	}
}
