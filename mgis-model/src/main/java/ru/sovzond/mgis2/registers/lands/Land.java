package ru.sovzond.mgis2.registers.lands;

import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;
import ru.sovzond.mgis2.registers.oks.Address;
import ru.sovzond.mgis2.registers.oks.rights.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lands_land")
public class Land implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String cadastralNumber;

	@Column
	private Date stateRealEstateCadastreaStaging;

	@OneToMany
	private List<LandArea> landAreas = new ArrayList<LandArea>();

	@ManyToOne
	private Person allowedUsageByDictionary;

	@ManyToOne
	private LandAllowedUsageByDocument allowedUsageByDocument;

	@ManyToOne
	private TerritorialZone allowedUsageByTerritorialZone;

	@ManyToOne
	private OKTMO addressOfMunicipalEntity;

	@ManyToOne
	private AddressPlacementType addressOfPlacementType;

	@ManyToOne
	private Address addressOfPlacement;

	@OneToOne
	private Land previousVersion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCadastralNumber() {
		return cadastralNumber;
	}

	public void setCadastralNumber(String cadastralNumber) {
		this.cadastralNumber = cadastralNumber;
	}

	public Date getStateRealEstateCadastreaStaging() {
		return stateRealEstateCadastreaStaging;
	}

	public void setStateRealEstateCadastreaStaging(Date stateRealEstateCadastreaStaging) {
		this.stateRealEstateCadastreaStaging = stateRealEstateCadastreaStaging;
	}

	public Person getAllowedUsageByDictionary() {
		return allowedUsageByDictionary;
	}

	public void setAllowedUsageByDictionary(Person allowedUsageByDictionary) {
		this.allowedUsageByDictionary = allowedUsageByDictionary;
	}

	public LandAllowedUsageByDocument getAllowedUsageByDocument() {
		return allowedUsageByDocument;
	}

	public void setAllowedUsageByDocument(LandAllowedUsageByDocument allowedUsageByDocument) {
		this.allowedUsageByDocument = allowedUsageByDocument;
	}

	public TerritorialZone getAllowedUsageByTerritorialZone() {
		return allowedUsageByTerritorialZone;
	}

	public void setAllowedUsageByTerritorialZone(TerritorialZone allowedUsageByTerritorialZone) {
		this.allowedUsageByTerritorialZone = allowedUsageByTerritorialZone;
	}

	public OKTMO getAddressOfMunicipalEntity() {
		return addressOfMunicipalEntity;
	}

	public void setAddressOfMunicipalEntity(OKTMO addressOfMunicipalEntity) {
		this.addressOfMunicipalEntity = addressOfMunicipalEntity;
	}

	public AddressPlacementType getAddressOfPlacementType() {
		return addressOfPlacementType;
	}

	public void setAddressOfPlacementType(AddressPlacementType addressOfPlacementType) {
		this.addressOfPlacementType = addressOfPlacementType;
	}

	public Address getAddressOfPlacement() {
		return addressOfPlacement;
	}

	public void setAddressOfPlacement(Address addressOfPlacement) {
		this.addressOfPlacement = addressOfPlacement;
	}

	public Land getPreviousVersion() {
		return previousVersion;
	}

	public void setPreviousVersion(Land previousVersion) {
		this.previousVersion = previousVersion;
	}

	@Override
	public Land clone() {
		Land land = new Land();
		land.setId(id);
		land.setCadastralNumber(cadastralNumber);
		// TODO: complete the clone procedure
		return land;
	}

	public List<LandArea> getLandAreas() {
		return landAreas;
	}

	public void setLandAreas(List<LandArea> landAreas) {
		this.landAreas = landAreas;
	}
}
