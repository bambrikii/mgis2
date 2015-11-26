package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
public class SpatialElementUnitDTO {

	public OrdinateDTO[] ordinates;
	private String typeUnit;
	private int suNumb;

	public OrdinateDTO[] getOrdinates() {
		return ordinates;
	}

	public void setOrdinates(OrdinateDTO[] ordinates) {
		this.ordinates = ordinates;
	}

	public void setTypeUnit(String typeUnit) {
		this.typeUnit = typeUnit;
	}

	public String getTypeUnit() {
		return typeUnit;
	}

	public void setSuNumb(int suNumb) {
		this.suNumb = suNumb;
	}

	public int getSuNumb() {
		return suNumb;
	}
}
