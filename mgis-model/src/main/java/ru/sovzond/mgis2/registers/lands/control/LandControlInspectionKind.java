package ru.sovzond.mgis2.registers.lands.control;

import javax.persistence.*;

@Entity
@Table(name = "lands_land_control_inspection_kind")
public class LandControlInspectionKind implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_land_control_inspection_kind_seq", allocationSize = 1)
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

	public LandControlInspectionKind clone() {
		LandControlInspectionKind kind = new LandControlInspectionKind();
		kind.setId(id);
		kind.setCode(code);
		kind.setName(name);
		return kind;
	}
}
