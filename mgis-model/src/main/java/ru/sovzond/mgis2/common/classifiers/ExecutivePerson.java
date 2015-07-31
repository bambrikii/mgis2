package ru.sovzond.mgis2.common.classifiers;

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

	@Column
	private String position;

	@Column(name = "full_name")
	private String fullName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
	}

	public ExecutivePerson clone() {
		ExecutivePerson person = new ExecutivePerson();
		person.setId(id);
		person.setPosition(position);
		person.setFullName(fullName);
		return person;
	}

}
