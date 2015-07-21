package ru.sovzond.mgis2.registers.lands;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.sovzond.mgis2.registers.administrative_territorial_division.MunicipalEntity;
import ru.sovzond.mgis2.registers.oks.Address;
import ru.sovzond.mgis2.registers.oks.CapitalConstruction;
import ru.sovzond.mgis2.registers.oks.rights.Person;
import ru.sovzond.mgis2.registers.territorial_division.TerritorialZone;

public class Land {
	private Long id;
	private String cadastralNumber;
	private Date stateRealEstateCadastreaStaging;
	private Person allowedUsageByDictionary;
	private LandAllowedUsageByDocument allowedUsageByDocument;
	private TerritorialZone allowedUsageByTerritorialZone;
	private MunicipalEntity addressOfMunicipalEntity;
	private AddressPlacementType addressOfPlacementType;
	private Address addressOfPlacement;
	private List<Land> includedLands = new ArrayList<Land>();
	private List<CapitalConstruction> includedCapitalConstructions = new ArrayList<CapitalConstruction>();
	private Land previousVersion;
}
