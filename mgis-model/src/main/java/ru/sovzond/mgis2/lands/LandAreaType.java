package ru.sovzond.mgis2.lands;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Entity
@Table(name = "lands_land_area_type")
public class LandAreaType implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_land_area_type_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
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

	public LandAreaType clone() {
		LandAreaType type = new LandAreaType();
		type.setId(id);
		type.setName(name);
		return type;
	}
}
