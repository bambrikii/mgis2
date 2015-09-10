package ru.sovzond.mgis2.registers.persons;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mgis2_legal_person")
public class LegalPerson extends Person {

	public LegalPerson clone() {
		LegalPerson person = new LegalPerson();
		person.setId(getId());
		person.setName(getName());
		return person;
	}
}
