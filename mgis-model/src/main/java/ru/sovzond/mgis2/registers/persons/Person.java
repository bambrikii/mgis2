package ru.sovzond.mgis2.registers.persons;

import javax.persistence.*;

@Entity
@Table(name = "mgis2_person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_person_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person clone() {
		Person person = new Person();
		person.setId(id);
		person.setName(name);
		return person;
	}
}
