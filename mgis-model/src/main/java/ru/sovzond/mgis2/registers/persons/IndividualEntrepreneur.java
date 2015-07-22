package ru.sovzond.mgis2.registers.persons;

import ru.sovzond.mgis2.registers.oks.rights.Person;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "oks_individual_entrepreneur")
public class IndividualEntrepreneur extends Person {

}
