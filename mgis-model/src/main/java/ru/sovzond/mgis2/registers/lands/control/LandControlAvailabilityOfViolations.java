package ru.sovzond.mgis2.registers.lands.control;

import javax.persistence.*;

@Entity
@Table(name = "lands_land_control_availability_of_violations")
public class LandControlAvailabilityOfViolations implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_land_control_availability_of_violations_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column(unique = true, nullable = false)
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

	public LandControlAvailabilityOfViolations clone() {
		LandControlAvailabilityOfViolations violations = new LandControlAvailabilityOfViolations();
		violations.setId(id);
		violations.setCode(code);
		violations.setName(name);
		return violations;
	}

}
