package ru.sovzond.mgis2.lands.control;

import javax.persistence.*;

@Entity
@Table(name = "lands_land_control_inspection_type")
public class LandControlInspectionType implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_land_control_inspection_type_seq", allocationSize = 1)
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

	public LandControlInspectionType clone() {
		LandControlInspectionType type = new LandControlInspectionType();
		type.setId(id);
		type.setCode(code);
		type.setName(name);
		return type;
	}

}
