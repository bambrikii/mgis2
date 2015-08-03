package ru.sovzond.mgis2.registers.lands;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Entity
@Table(name = "lands_land_area")
public class LandArea implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private Float value;

	@ManyToOne
	private LandAreaType landAreaType;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public LandAreaType getLandAreaType() {
		return landAreaType;
	}

	public void setLandAreaType(LandAreaType landAreaType) {
		this.landAreaType = landAreaType;
	}

	public LandArea clone() {
		LandArea area = new LandArea();
		area.setId(id);
		area.setValue(value);
		area.setLandAreaType(landAreaType.clone());
		return area;
	}
}
