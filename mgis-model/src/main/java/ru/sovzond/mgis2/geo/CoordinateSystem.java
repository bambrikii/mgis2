package ru.sovzond.mgis2.geo;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 05.10.15.
 */
@Entity
@Table(name = "mgis2_geo_coord_system")
public class CoordinateSystem implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_geo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Column(name = "conversion_rules", columnDefinition = "text")
	private String conversionRules;

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

	public String getConversionRules() {
		return conversionRules;
	}

	public void setConversionRules(String conversionRules) {
		this.conversionRules = conversionRules;
	}

	public CoordinateSystem clone() {
		CoordinateSystem coordinateSystem = new CoordinateSystem();
		coordinateSystem.setId(id);
		coordinateSystem.setCode(code);
		coordinateSystem.setConversionRules(conversionRules);
		return coordinateSystem;
	}
}
