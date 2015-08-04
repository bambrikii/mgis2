package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 04.08.15.
 */
@Entity
@Table(name = "nc_territorial_zone_type")
public class TerritorialZoneType implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_territorial_zone_type_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TerritorialZoneType clone() {
		TerritorialZoneType zoneType = new TerritorialZoneType();
		zoneType.setId(id);
		zoneType.setCode(code);
		zoneType.setName(name);
		return zoneType;
	}
}
