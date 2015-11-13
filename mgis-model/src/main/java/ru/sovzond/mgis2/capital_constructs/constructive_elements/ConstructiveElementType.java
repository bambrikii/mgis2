package ru.sovzond.mgis2.capital_constructs.constructive_elements;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 13.11.15.
 */
@Entity
@Table(name = "oks_constructive_element")
public class ConstructiveElementType implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(nullable = false, unique = true)
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

	public ConstructiveElementType clone() {
		ConstructiveElementType type = new ConstructiveElementType();
		type.setId(id);
		type.setName(name);
		return type;
	}
}
