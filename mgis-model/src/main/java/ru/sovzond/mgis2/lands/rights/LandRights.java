package ru.sovzond.mgis2.lands.rights;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.lands.Land;
import ru.sovzond.mgis2.registers.national_classifiers.LandEncumbrance;
import ru.sovzond.mgis2.rights.PropertyRights;

import javax.persistence.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "lands_land_right")
public class LandRights extends PropertyRights {

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

	@OneToOne(optional = false)
	@JoinColumn(name = "land_id")
	private Land land;

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

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public LandRights clone() {
		LandRights rights = new LandRights();
		rights.setId(getId());
		rights.setComment(comment);
		rights.setOwnershipDate(getOwnershipDate());
		rights.setTerminationDate(getTerminationDate());
		rights.setRightOwner(getRightOwner() != null ? getRightOwner().clone() : null);
		rights.setRightKind(getRightKind() != null ? getRightKind().clone() : null);
		rights.setOwnershipForm(getOwnershipForm() != null ? getOwnershipForm().clone() : null);
		rights.setEncumbrance(encumbrance != null ? encumbrance.clone() : null);
		rights.setObligations(obligations);
		rights.setShare(getShare());
		rights.setAnnualTax(annualTax);
		rights.setTotalArea(totalArea);
		rights.setRegistrationDocuments(getRegistrationDocuments() != null ? getRegistrationDocuments().stream().map(document -> {
			Document doc2 = new Document();
			doc2.setId(document.getId());
			doc2.setName(document.getName());
			return doc2;
		}).collect(Collectors.toList()) : null);
		rights.setDocumentsCertifyingRights(getDocumentsCertifyingRights() != null ? getDocumentsCertifyingRights().stream().map(document -> {
			Document doc2 = new Document();
			doc2.setId(document.getId());
			doc2.setName(document.getName());
			return doc2;
		}).collect(Collectors.toList()) : null);
		return rights;
	}

}
