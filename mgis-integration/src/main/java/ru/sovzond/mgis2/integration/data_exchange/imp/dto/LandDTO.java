package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

import java.util.Date;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
public class LandDTO {
	private String cadastralNumber;
	private String name;
	private String state;
	private Date dateCreated;
	private Double area;
	private String areaUnit;
	private String locationInBounds;
	private String locationPlaced;
	private AddressDTO address;
	private String category;
	private LandRightDTO[] rights;
	private Double cadastralCostValue;
	private Integer cadastralCostUnit;
	private String utilizationByDoc;
	private EntitySpatialDTO entitySpatial;

	public String getCadastralNumber() {
		return cadastralNumber;
	}

	public void setCadastralNumber(String cadastralNumber) {
		this.cadastralNumber = cadastralNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getAreaUnit() {
		return areaUnit;
	}

	public void setAreaUnit(String areaUnit) {
		this.areaUnit = areaUnit;
	}

	public String getLocationInBounds() {
		return locationInBounds;
	}

	public void setLocationInBounds(String locationInBounds) {
		this.locationInBounds = locationInBounds;
	}

	public String getLocationPlaced() {
		return locationPlaced;
	}

	public void setLocationPlaced(String locationPlaced) {
		this.locationPlaced = locationPlaced;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LandRightDTO[] getRights() {
		return rights;
	}

	public void setRights(LandRightDTO[] rights) {
		this.rights = rights;
	}

	public Double getCadastralCostValue() {
		return cadastralCostValue;
	}

	public void setCadastralCostValue(Double cadastralCostValue) {
		this.cadastralCostValue = cadastralCostValue;
	}

	public Integer getCadastralCostUnit() {
		return cadastralCostUnit;
	}

	public void setCadastralCostUnit(Integer cadastralCostUnit) {
		this.cadastralCostUnit = cadastralCostUnit;
	}


	public void setUtilizationByDoc(String utilizationByDoc) {
		this.utilizationByDoc = utilizationByDoc;
	}

	public String getUtilizationByDoc() {
		return utilizationByDoc;
	}

	public EntitySpatialDTO getEntitySpatial() {
		return entitySpatial;
	}

	public void setEntitySpatial(EntitySpatialDTO entitySpatial) {
		this.entitySpatial = entitySpatial;
	}
}
