package ru.sovzond.mgis2.lands.characteristics;

import javax.persistence.*;

@Entity
@Table(name = "lands_land_characteristics_engineering_support_area")
public class LandCharacteristicsEngineeringSupportArea implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LandCharacteristicsEngineeringSupportArea clone() {
		LandCharacteristicsEngineeringSupportArea area = new LandCharacteristicsEngineeringSupportArea();
		area.setId(id);
		area.setName(name);
		return area;
	}
}
