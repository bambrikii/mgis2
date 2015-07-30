package ru.sovzond.mgis2.registers.oks.rights;

import javax.persistence.*;

@Entity
@Table(name = "oks_person")
public class Person implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "oks_person_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person clone() {
		Person person = new Person();
		person.setId(id);
		return person;
	}
}
