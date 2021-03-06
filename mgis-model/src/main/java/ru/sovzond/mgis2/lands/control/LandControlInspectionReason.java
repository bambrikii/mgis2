package ru.sovzond.mgis2.lands.control;

import javax.persistence.*;

@Entity
@Table(name = "lands_land_control_inspection_reason")
public class LandControlInspectionReason {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_land_control_inspection_reason_seq", allocationSize = 1)
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


	public LandControlInspectionReason clone() {
		LandControlInspectionReason reason = new LandControlInspectionReason();
		reason.setId(id);
		reason.setCode(code);
		reason.setName(name);
		return reason;
	}
}
