package ru.sovzond.mgis2.geo;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 05.10.15.
 */
@Entity
@Table(name = "mgis_geo_coord_system")
public class CoordinateSystem implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_geo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CoordinateSystem clone() {
		CoordinateSystem coordinateSystem = new CoordinateSystem();
		coordinateSystem.setId(id);
		coordinateSystem.setCode(code);
		return coordinateSystem;
	}
}
