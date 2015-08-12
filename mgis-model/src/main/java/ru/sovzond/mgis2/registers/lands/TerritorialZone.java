package ru.sovzond.mgis2.registers.lands;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.national_classifiers.LandAllowedUsage;
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

	@Column
	private String accountNumber;

	@ManyToOne
	private LandAllowedUsage allowedUsageKind;

	@Column
	private String allowedUsageKindAsText;

	// TODO:
	// private LandAllowedUsageByClassifier allowedUsageByClassifier;

	@Column
	private String allowedUsageByDocument;

	@ManyToOne
	private Document basisDocument;

	@Column
	private Date stateOnTheDate;

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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public LandAllowedUsage getAllowedUsageKind() {
		return allowedUsageKind;
	}

	public void setAllowedUsageKind(LandAllowedUsage allowedUsageKind) {
		this.allowedUsageKind = allowedUsageKind;
	}

	public String getAllowedUsageKindAsText() {
		return allowedUsageKindAsText;
	}

	public void setAllowedUsageKindAsText(String allowedUsageKindAsText) {
		this.allowedUsageKindAsText = allowedUsageKindAsText;
	}

	public String getAllowedUsageByDocument() {
		return allowedUsageByDocument;
	}

	public void setAllowedUsageByDocument(String allowedUsageByDocument) {
		this.allowedUsageByDocument = allowedUsageByDocument;
	}

	public Document getBasisDocument() {
		return basisDocument;
	}

	public void setBasisDocument(Document basisDocument) {
		this.basisDocument = basisDocument;
	}

	public Date getStateOnTheDate() {
		return stateOnTheDate;
	}

	public void setStateOnTheDate(Date stateOnTheDate) {
		this.stateOnTheDate = stateOnTheDate;
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
		zone.setAccountNumber(accountNumber);
		zone.setAllowedUsageKind(allowedUsageKind != null ? allowedUsageKind.clone() : null);
		zone.setAllowedUsageKindAsText(allowedUsageKindAsText);
		zone.setAllowedUsageByDocument(allowedUsageByDocument);
		zone.setBasisDocument(basisDocument != null ? basisDocument.clone() : null);
		zone.setStateOnTheDate(stateOnTheDate);
		return zone;
	}

}
