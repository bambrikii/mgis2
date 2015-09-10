package ru.sovzond.mgis2.registers.persons;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mgis2_natural_person")
public class NaturalPerson extends Person {
	@Column
	private String firstName;

	@Column
	private String surname;

	@Column
	private String patronymic;

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

	public NaturalPerson clone() {
		NaturalPerson np = new NaturalPerson();
		np.setId(getId());
		np.setName(getName());
		np.setFirstName(firstName);
		np.setSurname(surname);
		np.setPatronymic(patronymic);
		return np;
	}
}
