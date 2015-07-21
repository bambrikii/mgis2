package ru.sovzond.mgis2.registers.lands;

import java.util.Date;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.oks.rights.Person;
import ru.sovzond.mgis2.registers.persons.LegalPerson;

public class LandWork {
	private Long id;
	private LandWorkType landWorkType;
	private LegalPerson executiveOrganization;
	private Person cadastralEngineer;
	private Document basisDocument;
	private Date startDate;
	private Date endDate;

}
