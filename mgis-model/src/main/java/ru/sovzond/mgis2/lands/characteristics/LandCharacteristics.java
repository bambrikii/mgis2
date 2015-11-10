package ru.sovzond.mgis2.lands.characteristics;

import ru.sovzond.mgis2.lands.Land;
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
	private float cadastralCost;

	@Column
	private Date cadastralCostImplementationDate;

	@Column
	private float specificIndexOfCadastralCost;

	@Column
	private float marketCost;

	@Column
	private Date marketCostImplementationDate;

	@Column
	private float mortgageValue;

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

	public float getCadastralCost() {
		return cadastralCost;
	}

	public void setCadastralCost(float cadastralCost) {
		this.cadastralCost = cadastralCost;
	}

	public float getSpecificIndexOfCadastralCost() {
		return specificIndexOfCadastralCost;
	}

	public void setSpecificIndexOfCadastralCost(float specificIndexOfCadastralCost) {
		this.specificIndexOfCadastralCost = specificIndexOfCadastralCost;
	}

	public float getMarketCost() {
		return marketCost;
	}

	public void setMarketCost(float marketCost) {
		this.marketCost = marketCost;
	}

	public float getMortgageValue() {
		return mortgageValue;
	}

	public void setMortgageValue(float mortgageValue) {
		this.mortgageValue = mortgageValue;
	}

	public Date getCadastralCostImplementationDate() {
		return cadastralCostImplementationDate;
	}

	public void setCadastralCostImplementationDate(Date cadastralCostImplementationDate) {
		this.cadastralCostImplementationDate = cadastralCostImplementationDate;
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

	public Date getMarketCostImplementationDate() {
		return marketCostImplementationDate;
	}

	public void setMarketCostImplementationDate(Date marketCostImplementationDate) {
		this.marketCostImplementationDate = marketCostImplementationDate;
	}

	public LandCharacteristics clone() {
		LandCharacteristics ch = new LandCharacteristics();
		ch.setId(id);
		ch.setCadastralCost(cadastralCost);
		ch.setSpecificIndexOfCadastralCost(specificIndexOfCadastralCost);
		ch.setMarketCost(marketCost);
		ch.setMortgageValue(mortgageValue);
		ch.setCadastralCostImplementationDate(cadastralCostImplementationDate);
		ch.setMarketCostImplementationDate(marketCostImplementationDate);
		ch.setStandardCost(standardCost);
		ch.setTypeOfEngineeringSupportArea(typeOfEngineeringSupportArea != null ? typeOfEngineeringSupportArea.clone() : null);
		ch.setDistanceToTheConnectionPoint(distanceToTheConnectionPoint);
		ch.setDistanceToTheConnectionPointLength(distanceToTheConnectionPointLength);
		ch.setDistanceToTheObjectsOfTransportInfrastructure(distanceToTheObjectsOfTransportInfrastructure);
		ch.setNearestMunicipalEntity(nearestMunicipalEntity != null ? nearestMunicipalEntity.clone() : null);
		ch.setDistanceToTheNearestMunicipalEntity(distanceToTheNearestMunicipalEntity);
		ch.setCountrySubject(countrySubject != null ? countrySubject.clone() : null);
		ch.setDistanceToTheCountrySubjectCenter(distanceToTheCountrySubjectCenter);
		return ch;
	}

}
