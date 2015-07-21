package ru.sovzond.mgis2.registers.lands;

import ru.sovzond.mgis2.registers.administrative_territorial_division.CountrySubject;
import ru.sovzond.mgis2.registers.administrative_territorial_division.MunicipalEntity;

import java.util.Date;

public class LandCharacteristics {
	private Land land;
	private float cadastralPrice;
	private float upOfCadastralPrice;
	private float marketPrice;
	private float mortgageValue;
	private Date implementationDate;
	private float standardCost;
	private LandCharacteristicsEngineeringSupportArea typeOfEngineeringSupportArea;
	private float distanceToTheConnectionPoint;
	private float distanceToTheConnectionPointLength;
	private float distanceToTheObjectsOfTransportInfrastructure;
	private MunicipalEntity nearestMunicipalEntity;
	private float distanceToTheNearstMunicipalEntity;
	private CountrySubject countrySubject;
	private float distanceToTheCountrySubjectCenter;
}
