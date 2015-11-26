package ru.sovzond.mgis2.address;

/**
 * Created by Alexander Arakelyan on 20.11.15.
 */
public class AddressFilterBuilder {
	private String subject;
	private String subjectType;
	private String region;
	private String regionType;
	private String district;
	private String districtType;
	private String locality;
	private String localityType;
	private String street;
	private String streetType;
	private String home;
	private String housing;
	private String building;
	private String apartment;

	public AddressFilterBuilder subject(String subject, String subjectType) {
		this.subject = subject;
		this.subjectType = subjectType;
		return this;
	}

	public AddressFilterBuilder region(String region, String regionType) {
		this.region = region;
		this.regionType = regionType;
		return this;
	}

	public AddressFilterBuilder district(String district, String districtType) {
		this.district = district;
		this.districtType = districtType;
		return this;
	}

	public AddressFilterBuilder locality(String locality, String localityType) {
		this.locality = locality;
		this.localityType = localityType;
		return this;
	}

	public AddressFilterBuilder street(String street, String streetType) {
		this.street = street;
		this.streetType = streetType;
		return this;
	}

	public AddressFilterBuilder home(String home) {
		this.home = home;
		return this;
	}

	public AddressFilterBuilder housing(String housing) {
		this.housing = housing;
		return this;
	}

	public AddressFilterBuilder building(String building) {
		this.building = building;
		return this;
	}

	public AddressFilterBuilder apartment(String apartment) {
		this.apartment = apartment;
		return this;
	}

	public AddressFilter build() {
		return new AddressFilter(
				subject, subjectType,
				region, regionType,
				district, districtType,
				locality, localityType,
				street, streetType,
				home,
				housing,
				building,
				apartment
		);
	}

}
