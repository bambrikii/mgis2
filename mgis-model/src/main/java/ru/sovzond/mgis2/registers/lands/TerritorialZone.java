package ru.sovzond.mgis2.registers.lands;

import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import javax.persistence.*;

@Entity
@Table(name = "lands_territorial_zone")
public class TerritorialZone implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_territorial_zone_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne
	private TerritorialZoneType zoneType;

	@Column
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TerritorialZoneType getZoneType() {
		return zoneType;
	}

	public void setZoneType(TerritorialZoneType zoneType) {
		this.zoneType = zoneType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TerritorialZone clone() {
		TerritorialZone zone = new TerritorialZone();
		zone.setId(id);
		zone.setZoneType(zoneType != null ? zoneType.clone() : null);
		zone.setName(name);
		return zone;
	}
}
