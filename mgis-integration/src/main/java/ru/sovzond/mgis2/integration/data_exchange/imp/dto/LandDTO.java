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
}
