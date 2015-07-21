package ru.sovzond.mgis2.registers.lands;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.oks.rights.Person;

public class LandControl {
	private Long id;
	private Land land;
	private Person inspectedPerson;
	private Person responsiblePerson;
	private Date checkDate;
	private LandControlCheckType checkType;
	private LandControlCheckForm checkForm;
	private LandControlInspectionReason inspectionReason;
	private LandControlCheckSubject checkSubject;
	private LandControlAvailabilityOfViolations checkResultAvailabilityOfViolations;
	private String checkResultDescription;
	private Date timelineForViolations;
	private float penaltyAmount;
	private List<Document> checkResultDocuments = new ArrayList<Document>();
}
