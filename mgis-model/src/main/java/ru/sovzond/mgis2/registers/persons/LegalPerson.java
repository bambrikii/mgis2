package ru.sovzond.mgis2.registers.persons;

import ru.sovzond.mgis2.registers.oks.rights.Person;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "oks_legal_person")
public class LegalPerson extends Person {

	public LegalPerson clone() {
		LegalPerson person = new LegalPerson();
		person.setId(getId());
		person.setName(getName());
		return person;
	}
}
