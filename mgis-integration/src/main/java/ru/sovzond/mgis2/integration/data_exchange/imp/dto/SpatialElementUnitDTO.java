package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
public class SpatialElementUnitDTO {

	public List<OrdinateDTO> ordinates;
	private String typeUnit;
	private int suNumb;

	public List<OrdinateDTO> getOrdinates() {
		return ordinates;
	}

	public void setOrdinates(List<OrdinateDTO> ordinates) {
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

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("typeUnit", typeUnit).append("suNumb", suNumb)
				.append("spatialElementUnits", ordinates).toString();
	}
}
