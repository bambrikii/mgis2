package ru.sovzond.mgis2.registers.lands;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.registers.oks.rights.Person;

public class LandRights {
	private Land land;
	private Person rightOwner;
	private LandRightType rightType;
	private LandOwnershipForm ownershipForm;
	private Date ownershipDate;
	private float share;
	private List<Document> registrationDocuments = new ArrayList<Document>();
	private List<Document> documentsCertifyingRights = new ArrayList<Document>();
	private float totalArea;
	private float taxPerYear;
	private boolean encumberance;
	private boolean obligations;
	private String comment;
}
