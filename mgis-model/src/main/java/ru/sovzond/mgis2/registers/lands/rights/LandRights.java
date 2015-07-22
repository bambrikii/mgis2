package ru.sovzond.mgis2.registers.lands.rights;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.lands.Land;
import ru.sovzond.mgis2.registers.oks.rights.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lands_land_right")
public class LandRights {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToOne(optional = false)
	private Land land;

	@ManyToOne
	private Person rightOwner;

	@ManyToOne
	private LandRightType rightType;

	@ManyToOne
	private LandOwnershipForm ownershipForm;

	@Column
	private Date ownershipDate;

	@Column
	private float share;

	@OneToMany
	private List<Document> registrationDocuments = new ArrayList<Document>();

	@OneToMany
	private List<Document> documentsCertifyingRights = new ArrayList<Document>();

	@Column
	private float totalArea;

	@Column
	private float taxPerYear;

	@Column
	private boolean encumberance;

	@Column
	private boolean obligations;

	@Column
	private String comment;

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

	public Person getRightOwner() {
		return rightOwner;
	}

	public void setRightOwner(Person rightOwner) {
		this.rightOwner = rightOwner;
	}

	public LandRightType getRightType() {
		return rightType;
	}

	public void setRightType(LandRightType rightType) {
		this.rightType = rightType;
	}

	public LandOwnershipForm getOwnershipForm() {
		return ownershipForm;
	}

	public void setOwnershipForm(LandOwnershipForm ownershipForm) {
		this.ownershipForm = ownershipForm;
	}

	public Date getOwnershipDate() {
		return ownershipDate;
	}

	public void setOwnershipDate(Date ownershipDate) {
		this.ownershipDate = ownershipDate;
	}

	public float getShare() {
		return share;
	}

	public void setShare(float share) {
		this.share = share;
	}

	public List<Document> getRegistrationDocuments() {
		return registrationDocuments;
	}

	public void setRegistrationDocuments(List<Document> registrationDocuments) {
		this.registrationDocuments = registrationDocuments;
	}

	public List<Document> getDocumentsCertifyingRights() {
		return documentsCertifyingRights;
	}

	public void setDocumentsCertifyingRights(List<Document> documentsCertifyingRights) {
		this.documentsCertifyingRights = documentsCertifyingRights;
	}

	public float getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(float totalArea) {
		this.totalArea = totalArea;
	}

	public float getTaxPerYear() {
		return taxPerYear;
	}

	public void setTaxPerYear(float taxPerYear) {
		this.taxPerYear = taxPerYear;
	}

	public boolean isEncumberance() {
		return encumberance;
	}

	public void setEncumberance(boolean encumberance) {
		this.encumberance = encumberance;
	}

	public boolean isObligations() {
		return obligations;
	}

	public void setObligations(boolean obligations) {
		this.obligations = obligations;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
