package ru.sovzond.mgis2.registers.lands.characteristics;

import ru.sovzond.mgis2.registers.lands.Land;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lands_land_characteristics")
public class LandCharacteristics implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToOne(optional = false)
	private Land land;

	@Column
	private float cadastralPrice;

	@Column
	private float upOfCadastralPrice;

	@Column
	private float marketPrice;

	@Column
	private float mortgageValue;

	@Column
	private Date implementationDate;

	@Column
	private float standardCost;

	@ManyToOne
	private LandCharacteristicsEngineeringSupportArea typeOfEngineeringSupportArea;

	@Column
	private float distanceToTheConnectionPoint;

	@Column
	private float distanceToTheConnectionPointLength;

	@Column
	private float distanceToTheObjectsOfTransportInfrastructure;

	@ManyToOne
	private OKTMO nearestMunicipalEntity;

	@Column
	private float distanceToTheNearestMunicipalEntity;

	@ManyToOne
	private OKATO countrySubject;

	@Column
	private float distanceToTheCountrySubjectCenter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public float getCadastralPrice() {
		return cadastralPrice;
	}

	public void setCadastralPrice(float cadastralPrice) {
		this.cadastralPrice = cadastralPrice;
	}

	public float getUpOfCadastralPrice() {
		return upOfCadastralPrice;
	}

	public void setUpOfCadastralPrice(float upOfCadastralPrice) {
		this.upOfCadastralPrice = upOfCadastralPrice;
	}

	public float getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(float marketPrice) {
		this.marketPrice = marketPrice;
	}

	public float getMortgageValue() {
		return mortgageValue;
	}

	public void setMortgageValue(float mortgageValue) {
		this.mortgageValue = mortgageValue;
	}

	public Date getImplementationDate() {
		return implementationDate;
	}

	public void setImplementationDate(Date implementationDate) {
		this.implementationDate = implementationDate;
	}

	public float getStandardCost() {
		return standardCost;
	}

	public void setStandardCost(float standardCost) {
		this.standardCost = standardCost;
	}

	public LandCharacteristicsEngineeringSupportArea getTypeOfEngineeringSupportArea() {
		return typeOfEngineeringSupportArea;
	}

	public void setTypeOfEngineeringSupportArea(LandCharacteristicsEngineeringSupportArea typeOfEngineeringSupportArea) {
		this.typeOfEngineeringSupportArea = typeOfEngineeringSupportArea;
	}

	public float getDistanceToTheConnectionPoint() {
		return distanceToTheConnectionPoint;
	}

	public void setDistanceToTheConnectionPoint(float distanceToTheConnectionPoint) {
		this.distanceToTheConnectionPoint = distanceToTheConnectionPoint;
	}

	public float getDistanceToTheConnectionPointLength() {
		return distanceToTheConnectionPointLength;
	}

	public void setDistanceToTheConnectionPointLength(float distanceToTheConnectionPointLength) {
		this.distanceToTheConnectionPointLength = distanceToTheConnectionPointLength;
	}

	public float getDistanceToTheObjectsOfTransportInfrastructure() {
		return distanceToTheObjectsOfTransportInfrastructure;
	}

	public void setDistanceToTheObjectsOfTransportInfrastructure(float distanceToTheObjectsOfTransportInfrastructure) {
		this.distanceToTheObjectsOfTransportInfrastructure = distanceToTheObjectsOfTransportInfrastructure;
	}

	public OKTMO getNearestMunicipalEntity() {
		return nearestMunicipalEntity;
	}

	public void setNearestMunicipalEntity(OKTMO nearestMunicipalEntity) {
		this.nearestMunicipalEntity = nearestMunicipalEntity;
	}

	public float getDistanceToTheNearestMunicipalEntity() {
		return distanceToTheNearestMunicipalEntity;
	}

	public void setDistanceToTheNearestMunicipalEntity(float distanceToTheNearestMunicipalEntity) {
		this.distanceToTheNearestMunicipalEntity = distanceToTheNearestMunicipalEntity;
	}

	public OKATO getCountrySubject() {
		return countrySubject;
	}

	public void setCountrySubject(OKATO countrySubject) {
		this.countrySubject = countrySubject;
	}

	public float getDistanceToTheCountrySubjectCenter() {
		return distanceToTheCountrySubjectCenter;
	}

	public void setDistanceToTheCountrySubjectCenter(float distanceToTheCountrySubjectCenter) {
		this.distanceToTheCountrySubjectCenter = distanceToTheCountrySubjectCenter;
	}

	public LandCharacteristics clone() {
		LandCharacteristics characteristics = new LandCharacteristics();
		characteristics.setId(id);
		// TODO:
		return characteristics;
	}
}
