package ru.sovzond.mgis2.lands;

import com.vividsolutions.jts.geom.MultiPolygon;
import org.hibernate.annotations.Type;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.geo.SpatialGroup;
import ru.sovzond.mgis2.lands.characteristics.LandCharacteristics;
import ru.sovzond.mgis2.lands.control.LandControl;
import ru.sovzond.mgis2.lands.includes.LandIncludedObjects;
import ru.sovzond.mgis2.lands.rights.LandRights;
import ru.sovzond.mgis2.lands.works.LandWorks;
import ru.sovzond.mgis2.registers.national_classifiers.LandAllowedUsage;
import ru.sovzond.mgis2.registers.national_classifiers.LandCategory;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

	@ManyToOne(fetch = FetchType.LAZY)
	private LandCategory landCategory;

	@Column
	private Date stateRealEstateCadastreaStaging;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<LandArea> landAreas = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private LandAllowedUsage allowedUsageByDictionary;

	@Column
	private String allowedUsageByDocument;

	@ManyToOne(fetch = FetchType.LAZY)
	private TerritorialZone allowedUsageByTerritorialZone;

	@ManyToOne(fetch = FetchType.LAZY)
	private OKTMO addressOfMunicipalEntity;

	@Column
	private String addressPlacement;

	@ManyToOne(fetch = FetchType.LAZY)
	private Address address;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private LandRights rights;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private LandCharacteristics characteristics;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private LandIncludedObjects includedObjects;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private LandWorks works;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private LandControl control;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private Land previousVersion;

	@Type(type = "org.hibernate.spatial.GeometryType")
	@Column(name = "geometry")
	private MultiPolygon geometry;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "spatial_data_id")
	private SpatialGroup spatialData;

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

	public LandRights getRights() {
		return rights;
	}

	public void setRights(LandRights rights) {
		this.rights = rights;
	}

	public LandCharacteristics getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(LandCharacteristics characteristics) {
		this.characteristics = characteristics;
	}

	public LandIncludedObjects getIncludedObjects() {
		return includedObjects;
	}

	public void setIncludedObjects(LandIncludedObjects includedObjects) {
		this.includedObjects = includedObjects;
	}

	public LandWorks getWorks() {
		return works;
	}

	public void setWorks(LandWorks works) {
		this.works = works;
	}

	public LandControl getControl() {
		return control;
	}

	public void setControl(LandControl control) {
		this.control = control;
	}

	public MultiPolygon getGeometry() {
		return geometry;
	}

	public void setGeometry(MultiPolygon geometry) {
		this.geometry = geometry;
	}

	public SpatialGroup getSpatialData() {
		return spatialData;
	}

	public void setSpatialData(SpatialGroup spatialData) {
		this.spatialData = spatialData;
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
		land.setAddressOfMunicipalEntity(addressOfMunicipalEntity != null ? addressOfMunicipalEntity.clone() : null);
		land.setAddressPlacement(addressPlacement);
		land.setAddress(address != null ? address.clone() : null);
		land.setRights(rights != null ? rights.clone() : null);
		land.setCharacteristics(characteristics != null ? characteristics.clone() : null);
		land.setControl(control != null ? control.clone() : null);
		land.getLandAreas().addAll(landAreas.stream().map(landArea1 -> landArea1.clone()).collect(Collectors.toList()));
		land.setSpatialData(spatialData != null ? spatialData.clone() : null);
		return land;
	}

}
