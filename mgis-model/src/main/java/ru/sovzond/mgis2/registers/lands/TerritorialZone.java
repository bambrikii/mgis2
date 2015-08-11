package ru.sovzond.mgis2.registers.lands;

import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lands_territorial_zone")
public class TerritorialZone implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_territorial_zone_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String name;

	@Column
	private Date creationDate;

	@Column
	private Date correctionDate;

	@Column
	private Date liquidationDate;

	@ManyToOne
	private OKTMO administrativeTerritorialEntity;

	@ManyToOne
	private TerritorialZoneType zoneType;

	@Column
	private Integer number;

	@Column
	private Integer index;

	@Column
	private String placement;

	@Column
	private String additionalDescription;

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


	public TerritorialZoneType getZoneType() {
		return zoneType;
	}

	public void setZoneType(TerritorialZoneType zoneType) {
		this.zoneType = zoneType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCorrectionDate() {
		return correctionDate;
	}

	public void setCorrectionDate(Date correctionDate) {
		this.correctionDate = correctionDate;
	}

	public Date getLiquidationDate() {
		return liquidationDate;
	}

	public void setLiquidationDate(Date liquidationDate) {
		this.liquidationDate = liquidationDate;
	}

	public OKTMO getAdministrativeTerritorialEntity() {
		return administrativeTerritorialEntity;
	}

	public void setAdministrativeTerritorialEntity(OKTMO administrativeTerritorialEntity) {
		this.administrativeTerritorialEntity = administrativeTerritorialEntity;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	public String getAdditionalDescription() {
		return additionalDescription;
	}

	public void setAdditionalDescription(String additionalDescription) {
		this.additionalDescription = additionalDescription;
	}

	public TerritorialZone clone() {
		TerritorialZone zone = new TerritorialZone();
		zone.setAdditionalDescription(additionalDescription);
		zone.setAdministrativeTerritorialEntity(administrativeTerritorialEntity != null ? administrativeTerritorialEntity.clone() : null);
		zone.setCorrectionDate(correctionDate);
		zone.setCreationDate(creationDate);
		zone.setId(id);
		zone.setIndex(index);
		zone.setLiquidationDate(liquidationDate);
		zone.setName(name);
		zone.setNumber(number);
		zone.setPlacement(placement);
		zone.setZoneType(zoneType != null ? zoneType.clone() : null);
		return zone;
	}

}
