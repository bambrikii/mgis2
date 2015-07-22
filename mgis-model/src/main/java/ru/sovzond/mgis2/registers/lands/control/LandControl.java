package ru.sovzond.mgis2.registers.lands.control;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.lands.Land;
import ru.sovzond.mgis2.registers.oks.rights.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lands_land_control")
public class LandControl {

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
	private Person responsiblePerson;

	@Column
	private Date checkDate;

	@ManyToOne
	private LandControlCheckType checkType;

	@ManyToOne
	private LandControlCheckForm checkForm;

	@ManyToOne
	private LandControlInspectionReason inspectionReason;

	@ManyToOne
	private LandControlCheckSubject checkSubject;

	@ManyToOne
	private LandControlAvailabilityOfViolations checkResultAvailabilityOfViolations;

	@Column
	private String checkResultDescription;

	@Column
	private Date timelineForViolations;

	@Column
	private float penaltyAmount;

	@OneToMany
	private List<Document> checkResultDocuments = new ArrayList<Document>();

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

	public Person getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(Person responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public LandControlCheckType getCheckType() {
		return checkType;
	}

	public void setCheckType(LandControlCheckType checkType) {
		this.checkType = checkType;
	}

	public LandControlCheckForm getCheckForm() {
		return checkForm;
	}

	public void setCheckForm(LandControlCheckForm checkForm) {
		this.checkForm = checkForm;
	}

	public LandControlInspectionReason getInspectionReason() {
		return inspectionReason;
	}

	public void setInspectionReason(LandControlInspectionReason inspectionReason) {
		this.inspectionReason = inspectionReason;
	}

	public LandControlCheckSubject getCheckSubject() {
		return checkSubject;
	}

	public void setCheckSubject(LandControlCheckSubject checkSubject) {
		this.checkSubject = checkSubject;
	}

	public LandControlAvailabilityOfViolations getCheckResultAvailabilityOfViolations() {
		return checkResultAvailabilityOfViolations;
	}

	public void setCheckResultAvailabilityOfViolations(LandControlAvailabilityOfViolations checkResultAvailabilityOfViolations) {
		this.checkResultAvailabilityOfViolations = checkResultAvailabilityOfViolations;
	}

	public String getCheckResultDescription() {
		return checkResultDescription;
	}

	public void setCheckResultDescription(String checkResultDescription) {
		this.checkResultDescription = checkResultDescription;
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

	public List<Document> getCheckResultDocuments() {
		return checkResultDocuments;
	}

	public void setCheckResultDocuments(List<Document> checkResultDocuments) {
		this.checkResultDocuments = checkResultDocuments;
	}
}
