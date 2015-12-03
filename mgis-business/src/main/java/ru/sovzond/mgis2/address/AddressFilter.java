package ru.sovzond.mgis2.address;

/**
 * Created by Alexander Arakelyan on 20.11.15.
 */
class AddressFilter {
	private final String subject;
	private final String subjectType;
	private final String subjectCode;
	private final String region;
	private final String regionType;
	private final String locality;
	private final String localityType;
	private final String street;
	private final String streetType;
	private final String home;
	private final String housing;
	private final String apartment;
	private final String building;

	public AddressFilter(String subject, String subjectType, //
						 String subjectCode, //
						 String region, String regionType, //
						 String locality, String localityType, //
						 String street, String streetType, //
						 String home, //
						 String housing, //
						 String building, //
						 String apartment //
	) {
		this.subject = subject;
		this.subjectType = subjectType;
		this.region = region;
		this.regionType = regionType;
		this.subjectCode = subjectCode;
		this.locality = locality;
		this.localityType = localityType;
		this.street = street;
		this.streetType = streetType;
		this.home = home;
		this.housing = housing;
		this.building = building;
		this.apartment = apartment;
	}

	public String getSubject() {
		return subject;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public boolean subject() {
		return subject != null && subject.length() > 0 && subjectType != null && subjectType.length() > 0;
	}

	public String getRegion() {
		return region;
	}

	public String getRegionType() {
		return regionType;
	}

	public boolean region() {
		return region != null && region.length() > 0 && regionType != null && regionType.length() > 0;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public boolean subjectCode() {
		return subjectCode != null && subjectCode.length() > 0;
	}

	public String getLocality() {
		return locality;
	}

	public String getLocalityType() {
		return localityType;
	}

	public boolean locality() {
		return locality != null && locality.length() > 0 && localityType != null && localityType.length() > 0;
	}

	public String getStreet() {
		return street;
	}

	public String getStreetType() {
		return streetType;
	}

	public boolean street() {
		return street != null && street.length() > 0 && streetType != null && streetType.length() > 0;
	}


	public String getHome() {
		return home;
	}

	public boolean home() {
		return home != null && home.length() > 0;
	}

	public String getHousing() {
		return housing;
	}

	public boolean housing() {
		return housing != null && housing.length() > 0;
	}

	public String getBuilding() {
		return building;
	}

	public boolean building() {
		return building != null && building.length() > 0;
	}

	public String getApartment() {
		return apartment;
	}

	public boolean apartment() {
		return apartment != null && apartment.length() > 0;
	}

}
