package ru.sovzond.mgis2.capital_constructs.constructive_elements;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
@Entity
@Table(name = "oks_constructive_element")
public class ConstructiveElement implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String technicalCondition;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTechnicalCondition() {
		return technicalCondition;
	}

	public void setTechnicalCondition(String technicalCondition) {
		this.technicalCondition = technicalCondition;
	}

	public ConstructiveElement clone() {
		ConstructiveElement element = new ConstructiveElement();
		element.setName(name);
		element.setId(id);
		element.setDescription(description);
		element.setTechnicalCondition(technicalCondition);
		return element;
	}
}
