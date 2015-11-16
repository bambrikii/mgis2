package ru.sovzond.mgis2.lands.includes;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.lands.Land;
import ru.sovzond.mgis2.registers.oks.RusRegisterCapitalConstruction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 22.07.15.
 */
@Entity
@Table(name = "lands_included_objects")
public class LandIncludedObjects {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne
	private Document landDealDocument;

	@OneToMany
	private List<Land> includedLands = new ArrayList<>();

	@ManyToOne
	private Document inventoryDealDocument;

	@OneToMany
	private List<RusRegisterCapitalConstruction> includedCapitalConstructions = new ArrayList<>();

	@OneToMany
	private List<Document> urbanPlanningDocuments = new ArrayList<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Document getLandDealDocument() {
		return landDealDocument;
	}

	public void setLandDealDocument(Document landDealDocument) {
		this.landDealDocument = landDealDocument;
	}


	public List<Land> getIncludedLands() {
		return includedLands;
	}

	public void setIncludedLands(List<Land> includedLands) {
		this.includedLands = includedLands;
	}

	public Document getInventoryDealDocument() {
		return inventoryDealDocument;
	}

	public void setInventoryDealDocument(Document inventoryDealDocument) {
		this.inventoryDealDocument = inventoryDealDocument;
	}

	public List<RusRegisterCapitalConstruction> getIncludedCapitalConstructions() {
		return includedCapitalConstructions;
	}

	public void setIncludedCapitalConstructions(List<RusRegisterCapitalConstruction> includedCapitalConstructions) {
		this.includedCapitalConstructions = includedCapitalConstructions;
	}

	public List<Document> getUrbanPlanningDocuments() {
		return urbanPlanningDocuments;
	}

	public void setUrbanPlanningDocuments(List<Document> urbanPlanningDocuments) {
		this.urbanPlanningDocuments = urbanPlanningDocuments;
	}

}
