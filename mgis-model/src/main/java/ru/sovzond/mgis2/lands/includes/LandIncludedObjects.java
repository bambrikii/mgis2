package ru.sovzond.mgis2.lands.includes;

import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.lands.Land;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.07.15.
 */
@Entity
@Table(name = "lands_included_objects")
public class LandIncludedObjects implements Cloneable {

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
	private List<CapitalConstruction> includedCapitalConstructions = new ArrayList<>();

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

	public List<CapitalConstruction> getIncludedCapitalConstructions() {
		return includedCapitalConstructions;
	}

	public void setIncludedCapitalConstructions(List<CapitalConstruction> includedCapitalConstructions) {
		this.includedCapitalConstructions = includedCapitalConstructions;
	}

	public List<Document> getUrbanPlanningDocuments() {
		return urbanPlanningDocuments;
	}

	public void setUrbanPlanningDocuments(List<Document> urbanPlanningDocuments) {
		this.urbanPlanningDocuments = urbanPlanningDocuments;
	}

	public LandIncludedObjects clone() {
		LandIncludedObjects ic = new LandIncludedObjects();
		ic.setId(id);
		ic.getIncludedLands().addAll(includedLands.stream().map(land -> {
			Land land1 = new Land();
			land1.setId(id);
			land1.setCadastralNumber(land.getCadastralNumber());
			land1.setStateRealEstateCadastreaStaging(land.getStateRealEstateCadastreaStaging());
			land1.setLandCategory(land.getLandCategory() != null ? land.getLandCategory().clone() : null);
			land1.setAddress(land.getAddress() != null ? land.getAddress().clone() : null);
			return land1;
		}).collect(Collectors.toList()));
		ic.getIncludedCapitalConstructions().addAll(includedCapitalConstructions.stream().map(capitalConstruction -> {
			CapitalConstruction cc1 = new CapitalConstruction();
			cc1.setCadastralNumber(capitalConstruction.getCadastralNumber());
			cc1.setName(capitalConstruction.getName());
			cc1.setType(capitalConstruction.getType() != null ? capitalConstruction.getType().clone() : null);
			cc1.setAddress(capitalConstruction.getAddress() != null ? capitalConstruction.getAddress().clone() : null);
			return cc1;
		}).collect(Collectors.toList()));
		ic.setInventoryDealDocument(inventoryDealDocument != null ? inventoryDealDocument.clone() : null);
		ic.setLandDealDocument(landDealDocument != null ? landDealDocument.clone() : null);
		ic.setUrbanPlanningDocuments(urbanPlanningDocuments.stream().map(document -> document.clone()).collect(Collectors.toList()));
		return ic;
	}
}
