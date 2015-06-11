package ru.sovzond.mgis2.registers.oks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rosreg_oks_address")
public class Address {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Integer id;

	// private String okato;
	// private String kladr;
	// private String oktmo;
	// private String postalCode;

	@Column
	private String region;

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

}
