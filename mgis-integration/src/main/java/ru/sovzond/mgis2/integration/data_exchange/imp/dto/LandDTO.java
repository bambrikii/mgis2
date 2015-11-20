package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
public class LandDTO {
	public String cadastralNumber;
	public String name;
	public String state;
	public String dateCreated;
	public String area;
	public String areaUnit;
	public String locationInBounds;
	public String locationPlaced;
	public AddressDTO address;
	public String category;
	public LandRightDTO[] rights;
	public Double cadastralCost;
	public Integer cadastralCostUnit;

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

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
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

	public Double getCadastralCost() {
		return cadastralCost;
	}

	public void setCadastralCost(Double cadastralCost) {
		this.cadastralCost = cadastralCost;
	}

	public Integer getCadastralCostUnit() {
		return cadastralCostUnit;
	}

	public void setCadastralCostUnit(Integer cadastralCostUnit) {
		this.cadastralCostUnit = cadastralCostUnit;
	}

}
