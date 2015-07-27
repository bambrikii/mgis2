package ru.sovzond.mgis2.registers.oks;

import javax.persistence.*;

@Entity
@Table(name = "rosreg_oks_address")
public class Address {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	// private String okato;
	// private String kladr;
	// private String oktmo;
	// private String postalCode;

	// private String district;
	// private RosRegName city;
	// private UrbanDistrict urbanDistrict;
	// private String SovereignityVillage;
	// private RosRegName locality;
	// private RosRegName Street;
	// private Level1 level1;
	// private Level2 level2;
	// private Level3 level3;
	// private Apartment apartment;
	// private String other;
	// private String note;

	@Column
	private String region;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
