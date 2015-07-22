package ru.sovzond.mgis2.registers.lands;

import javax.persistence.*;

@Entity
@Table(name = "territorial_zone")
public class TerritorialZone {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "land_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
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
