package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
public class SpatialElementUnitDTO {
	private String typeUnit;
	private int suNumb;
	private OrdinateDTO[] ordinates;

	public String getTypeUnit() {
		return typeUnit;
	}

	public void setTypeUnit(String typeUnit) {
		this.typeUnit = typeUnit;
	}

	public int getSuNumb() {
		return suNumb;
	}

	public void setSuNumb(int suNumb) {
		this.suNumb = suNumb;
	}

	public OrdinateDTO[] getOrdinates() {
		return ordinates;
	}

	public void setOrdinates(OrdinateDTO[] ordinates) {
		this.ordinates = ordinates;
	}
}
