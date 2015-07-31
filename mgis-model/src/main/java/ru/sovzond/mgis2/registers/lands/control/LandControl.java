package ru.sovzond.mgis2.registers.lands.control;

import ru.sovzond.mgis2.common.classifiers.ExecutivePerson;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.lands.Land;
import ru.sovzond.mgis2.registers.oks.rights.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lands_land_control")
public class LandControl implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "lands_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToOne(optional = false)
	private Land land;

	@ManyToOne
	private Person inspectedPerson;

	@ManyToOne
	private ExecutivePerson executivePerson;

	@Column
	private Date inspectionDate;

	@ManyToOne
	private LandControlInspectionType inspectionType;

	@ManyToOne
	private LandControlInspectionKind inspectionKind;

	@ManyToOne
	private LandControlInspectionReason inspectionReason;

	@ManyToOne
	private LandControlInspectionSubject inspectionSubject;

	@Column
	private String inspectionReasonDescription;

	@ManyToOne
	private LandControlAvailabilityOfViolations inspectionResultAvailabilityOfViolations;

	@Column
	private String inspectionResultDescription;

	@Column
	private Date timelineForViolations;

	@Column
	private float penaltyAmount;

	@OneToMany
	private List<Document> inspectionResultDocuments = new ArrayList<Document>();

	public LandControl() {
	}

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

	public Person getInspectedPerson() {
		return inspectedPerson;
	}

	public void setInspectedPerson(Person inspectedPerson) {
		this.inspectedPerson = inspectedPerson;
	}

	public ExecutivePerson getExecutivePerson() {
		return executivePerson;
	}

	public void setExecutivePerson(ExecutivePerson executivePerson) {
		this.executivePerson = executivePerson;
	}

	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public LandControlInspectionType getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(LandControlInspectionType inspectionType) {
		this.inspectionType = inspectionType;
	}

	public LandControlInspectionKind getInspectionKind() {
		return inspectionKind;
	}

	public void setInspectionKind(LandControlInspectionKind inspectionKind) {
		this.inspectionKind = inspectionKind;
	}

	public LandControlInspectionReason getInspectionReason() {
		return inspectionReason;
	}

	public void setInspectionReason(LandControlInspectionReason inspectionReason) {
		this.inspectionReason = inspectionReason;
	}

	public LandControlInspectionSubject getInspectionSubject() {
		return inspectionSubject;
	}

	public void setInspectionSubject(LandControlInspectionSubject inspectionSubject) {
		this.inspectionSubject = inspectionSubject;
	}

	public String getInspectionReasonDescription() {
		return inspectionReasonDescription;
	}

	public void setInspectionReasonDescription(String inspectionReasonDescription) {
		this.inspectionReasonDescription = inspectionReasonDescription;
	}

	public LandControlAvailabilityOfViolations getInspectionResultAvailabilityOfViolations() {
		return inspectionResultAvailabilityOfViolations;
	}

	public void setInspectionResultAvailabilityOfViolations(LandControlAvailabilityOfViolations inspectionResultAvailabilityOfViolations) {
		this.inspectionResultAvailabilityOfViolations = inspectionResultAvailabilityOfViolations;
	}

	public String getInspectionResultDescription() {
		return inspectionResultDescription;
	}

	public void setInspectionResultDescription(String inspectionResultDescription) {
		this.inspectionResultDescription = inspectionResultDescription;
	}

	public Date getTimelineForViolations() {
		return timelineForViolations;
	}

	public void setTimelineForViolations(Date timelineForViolations) {
		this.timelineForViolations = timelineForViolations;
	}

	public float getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(float penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	public List<Document> getInspectionResultDocuments() {
		return inspectionResultDocuments;
	}

	public void setInspectionResultDocuments(List<Document> inspectionResultDocuments) {
		this.inspectionResultDocuments = inspectionResultDocuments;
	}

	public LandControl clone() {
		LandControl control = new LandControl();
		control.setId(id);
		control.setExecutivePerson(executivePerson != null ? executivePerson.clone() : null);
		control.setInspectedPerson(inspectedPerson != null ? inspectedPerson.clone() : null);
		control.setInspectionDate(inspectionDate);
		control.setInspectionKind(inspectionKind != null ? inspectionKind.clone() : null);
		control.setInspectionReason(inspectionReason != null ? inspectionReason.clone() : null);
		control.setInspectionReasonDescription(inspectionReasonDescription);
		control.setInspectionResultAvailabilityOfViolations(inspectionResultAvailabilityOfViolations != null ? inspectionResultAvailabilityOfViolations.clone() : null);
		control.setInspectionResultDescription(inspectionResultDescription);
		control.setInspectionSubject(inspectionSubject != null ? inspectionSubject.clone() : null);
		control.setInspectionType(inspectionType != null ? inspectionType.clone() : null);
		control.setPenaltyAmount(penaltyAmount);
		control.setTimelineForViolations(timelineForViolations);
		return control;
	}

}
