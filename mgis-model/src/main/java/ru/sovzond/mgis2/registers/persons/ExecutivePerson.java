package ru.sovzond.mgis2.registers.persons;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Entity
@Table(name = "common_executive_person")
public class ExecutivePerson implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "common_executive_person_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;
	/**
	 * ФИО
	 */
	@Column
	private String firstName;

	@Column
	private String surname;

	@Column
	private String patronymic;

	@Column
	private String position;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}


	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	public ExecutivePerson clone() {
		ExecutivePerson person = new ExecutivePerson();
		person.setId(id);
		person.setFirstName(firstName);
		person.setSurname(surname);
		person.setPatronymic(patronymic);
		person.setPosition(position);
		return person;
	}

}
