package ru.sovzond.mgis2.registers.lands.rights;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.lands.Land;
import ru.sovzond.mgis2.registers.national_classifiers.LandEncumbrance;
import ru.sovzond.mgis2.registers.national_classifiers.OKFS;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;
import ru.sovzond.mgis2.registers.persons.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "lands_land_right")
public class LandRights implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToOne(optional = false)
	private Land land;

	@ManyToOne
	private LandRightKind rightKind;

	@ManyToOne
	private Person rightOwner;

	@ManyToOne
	private OKFS ownershipForm;

	@Column
	private Date ownershipDate;

	@Column
	private Date terminationDate;

	@Column
	private float share;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "lands_land_right_reg_docs", joinColumns = @JoinColumn(name = "land_land_right_id"), inverseJoinColumns = @JoinColumn(name = "registration_doc_id"))
	private List<Document> registrationDocuments = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "lands_land_right_cert_docs", joinColumns = @JoinColumn(name = "land_land_right_id"), inverseJoinColumns = @JoinColumn(name = "cert_doc_id"))
	private List<Document> documentsCertifyingRights = new ArrayList<>();

	@Column
	private float totalArea;

	@Column
	private float annualTax;

	@ManyToOne
	private LandEncumbrance encumbrance;

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

	public LandRightKind getRightKind() {
		return rightKind;
	}

	public void setRightKind(LandRightKind rightKind) {
		this.rightKind = rightKind;
	}

	public Person getRightOwner() {
		return rightOwner;
	}

	public void setRightOwner(Person rightOwner) {
		this.rightOwner = rightOwner;
	}

	public OKFS getOwnershipForm() {
		return ownershipForm;
	}

	public void setOwnershipForm(OKFS ownershipForm) {
		this.ownershipForm = ownershipForm;
	}

	public Date getOwnershipDate() {
		return ownershipDate;
	}

	public void setOwnershipDate(Date ownershipDate) {
		this.ownershipDate = ownershipDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
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

	public float getAnnualTax() {
		return annualTax;
	}

	public void setAnnualTax(float annualTax) {
		this.annualTax = annualTax;
	}

	public LandEncumbrance getEncumbrance() {
		return encumbrance;
	}

	public void setEncumbrance(LandEncumbrance encumbrance) {
		this.encumbrance = encumbrance;
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

	public LandRights clone() {
		LandRights rights = new LandRights();
		rights.setId(id);
		rights.setComment(comment);
		rights.setOwnershipDate(ownershipDate);
		rights.setTerminationDate(terminationDate);
		rights.setRightOwner(rightOwner != null ? rightOwner.clone() : null);
		rights.setRightKind(rightKind != null ? rightKind.clone() : null);
		rights.setOwnershipForm(ownershipForm != null ? ownershipForm.clone() : null);
		rights.setEncumbrance(encumbrance != null ? encumbrance.clone() : null);
		rights.setObligations(obligations);
		rights.setShare(share);
		rights.setAnnualTax(annualTax);
		rights.setTotalArea(totalArea);
		rights.setRegistrationDocuments(registrationDocuments != null ? registrationDocuments.stream().map(document -> {
			Document doc2 = new Document();
			doc2.setId(document.getId());
			doc2.setName(document.getName());
			return doc2;
		}).collect(Collectors.toList()) : null);
		rights.setDocumentsCertifyingRights(documentsCertifyingRights != null ? documentsCertifyingRights.stream().map(document -> {
			Document doc2 = new Document();
			doc2.setId(document.getId());
			doc2.setName(document.getName());
			return doc2;
		}).collect(Collectors.toList()) : null);
		return rights;
	}

}
