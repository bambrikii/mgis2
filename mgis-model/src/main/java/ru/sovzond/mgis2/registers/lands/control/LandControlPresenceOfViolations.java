package ru.sovzond.mgis2.registers.lands.control;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Entity
@Table(name = "lands_land_control_presence_of_violations")
public class LandControlPresenceOfViolations {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
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
}
