package ru.sovzond.mgis2.registers.lands.works;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.oks.rights.Person;
import ru.sovzond.mgis2.registers.persons.LegalPerson;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lands_land_work")
public class LandWork {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne
	private LandWorkType landWorkType;

	@ManyToOne
	private LegalPerson executiveOrganization;

	@ManyToOne
	private Person cadastralEngineer;

	@ManyToOne
	private Document basisDocument;

	@Column
	private Date startDate;

	@Column
	private Date endDate;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LandWorkType getLandWorkType() {
		return landWorkType;
	}

	public void setLandWorkType(LandWorkType landWorkType) {
		this.landWorkType = landWorkType;
	}

	public LegalPerson getExecutiveOrganization() {
		return executiveOrganization;
	}

	public void setExecutiveOrganization(LegalPerson executiveOrganization) {
		this.executiveOrganization = executiveOrganization;
	}

	public Person getCadastralEngineer() {
		return cadastralEngineer;
	}

	public void setCadastralEngineer(Person cadastralEngineer) {
		this.cadastralEngineer = cadastralEngineer;
	}

	public Document getBasisDocument() {
		return basisDocument;
	}

	public void setBasisDocument(Document basisDocument) {
		this.basisDocument = basisDocument;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
