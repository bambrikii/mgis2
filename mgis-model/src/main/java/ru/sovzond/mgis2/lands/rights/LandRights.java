package ru.sovzond.mgis2.lands.rights;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.national_classifiers.LandEncumbrance;
import ru.sovzond.mgis2.rights.PropertyRights;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.stream.Collectors;

@Entity
@Table(name = "lands_land_right")
@OnDelete(action = OnDeleteAction.CASCADE)
public class LandRights extends PropertyRights {

	public LandRights clone() {
		LandRights rights = new LandRights();
		rights.setId(getId());
		rights.setOwnershipDate(getOwnershipDate());
		rights.setTerminationDate(getTerminationDate());
		rights.setRightOwner(getRightOwner() != null ? getRightOwner().clone() : null);
		rights.setRightKind(getRightKind() != null ? getRightKind().clone() : null);
		rights.setOwnershipForm(getOwnershipForm() != null ? getOwnershipForm().clone() : null);
		rights.setShare(getShare());
		rights.setRegistrationDocuments(getRegistrationDocuments() != null ? getRegistrationDocuments().stream().map(document -> new Document(document.getId(), document.getName())).collect(Collectors.toList()) : null);
		rights.setDocumentsCertifyingRights(getDocumentsCertifyingRights() != null ? getDocumentsCertifyingRights().stream().map(document -> new Document(document.getId(), document.getName())).collect(Collectors.toList()) : null);
		rights.setOtherDocuments(getOtherDocuments() != null ? getOtherDocuments().stream().map(document -> new Document(document.getId(), document.getName())).collect(Collectors.toList()) : null);
		rights.setComment(getComment());
		rights.setEncumbrance(getEncumbrance() != null ? getEncumbrance().clone() : null);
		rights.setObligations(isObligations());
		rights.setAnnualTax(getAnnualTax());
		rights.setTotalArea(getTotalArea());
		return rights;
	}

}
