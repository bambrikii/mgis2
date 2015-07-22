package ru.sovzond.mgis2.registers.lands.works;

import ru.sovzond.mgis2.registers.lands.Land;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lands_land_works")
public class LandWorks {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToOne(optional = false)
	private Land land;

	@OneToMany
	private List<LandWork> landWork = new ArrayList<LandWork>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public List<LandWork> getLandWork() {
		return landWork;
	}

	public void setLandWork(List<LandWork> landWork) {
		this.landWork = landWork;
	}
}
