package ru.sovzond.mgis2.rights;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;
import ru.sovzond.mgis2.registers.national_classifiers.OKFS;
import ru.sovzond.mgis2.registers.persons.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 06.11.15.
 */
@Entity
@Table(name = "mgis2_property_right")
@Inheritance(strategy = InheritanceType.JOINED)
public class PropertyRights implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

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
	@JoinTable(name = "mgis2_property_rights_reg_docs", joinColumns = @JoinColumn(name = "mgis2_property_rights_id"), inverseJoinColumns = @JoinColumn(name = "registration_doc_id"))
	private List<Document> registrationDocuments = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "mgis2_property_rights_cert_docs", joinColumns = @JoinColumn(name = "mgis2_property_rights_id"), inverseJoinColumns = @JoinColumn(name = "cert_doc_id"))
	private List<Document> documentsCertifyingRights = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "mgis2_property_rights_other_docs", joinColumns = @JoinColumn(name = "mgis2_property_rights_id"), inverseJoinColumns = @JoinColumn(name = "other_doc_id"))
	private List<Document> otherDocuments = new ArrayList<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Document> getOtherDocuments() {
		return otherDocuments;
	}

	public void setOtherDocuments(List<Document> otherDocuments) {
		this.otherDocuments = otherDocuments;
	}

	public PropertyRights clone() {
		PropertyRights rights = new PropertyRights();
		rights.setId(id);
		rights.setOwnershipDate(ownershipDate);
		rights.setTerminationDate(terminationDate);
		rights.setRightOwner(rightOwner != null ? rightOwner.clone() : null);
		rights.setRightKind(rightKind != null ? rightKind.clone() : null);
		rights.setOwnershipForm(ownershipForm != null ? ownershipForm.clone() : null);
		rights.setShare(share);
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
		rights.setDocumentsCertifyingRights(otherDocuments != null ? otherDocuments.stream().map(document -> {
			Document doc2 = new Document();
			doc2.setId(document.getId());
			doc2.setName(document.getName());
			return doc2;
		}).collect(Collectors.toList()) : null);
		return rights;
	}
}
