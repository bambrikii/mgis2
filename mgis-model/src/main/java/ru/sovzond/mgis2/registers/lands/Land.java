package ru.sovzond.mgis2.registers.lands;

import ru.sovzond.mgis2.registers.national_classifiers.LandAllowedUsage;
import ru.sovzond.mgis2.registers.national_classifiers.LandCategory;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZone;
import ru.sovzond.mgis2.registers.oks.Address;

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

	@ManyToOne
	private LandCategory landCategory;

	@Column
	private Date stateRealEstateCadastreaStaging;

	@OneToMany
	private List<LandArea> landAreas = new ArrayList<>();

	@ManyToOne
	private LandAllowedUsage allowedUsageByDictionary;

	@Column
	private String allowedUsageByDocument;

	@ManyToOne
	private TerritorialZone allowedUsageByTerritorialZone;

	@ManyToOne
	private OKTMO addressOfMunicipalEntity;

	@Column
	private String addressPlacement;

	@ManyToOne
	private Address address;

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

	public LandAllowedUsage getAllowedUsageByDictionary() {
		return allowedUsageByDictionary;
	}

	public void setAllowedUsageByDictionary(LandAllowedUsage allowedUsageByDictionary) {
		this.allowedUsageByDictionary = allowedUsageByDictionary;
	}

	public String getAllowedUsageByDocument() {
		return allowedUsageByDocument;
	}

	public void setAllowedUsageByDocument(String allowedUsageByDocument) {
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

	public String getAddressPlacement() {
		return addressPlacement;
	}

	public void setAddressPlacement(String addressPlacement) {
		this.addressPlacement = addressPlacement;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
		land.setStateRealEstateCadastreaStaging(stateRealEstateCadastreaStaging);
		land.setAllowedUsageByDictionary(allowedUsageByDictionary != null ? allowedUsageByDictionary.clone() : null);
		land.setAllowedUsageByDocument(allowedUsageByDocument);
		land.setAllowedUsageByTerritorialZone(allowedUsageByTerritorialZone != null ? allowedUsageByTerritorialZone.clone() : null);
		land.setLandCategory(landCategory != null ? landCategory.clone() : null);
		land.setAddressPlacement(addressPlacement);
		land.setAddress(address != null ? address.clone() : null);
		// TODO: complete the clone procedure
		return land;
	}

	public List<LandArea> getLandAreas() {
		return landAreas;
	}

	public void setLandAreas(List<LandArea> landAreas) {
		this.landAreas = landAreas;
	}

	public LandCategory getLandCategory() {
		return landCategory;
	}

	public void setLandCategory(LandCategory landCategory) {
		this.landCategory = landCategory;
	}
}
