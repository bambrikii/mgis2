package ru.sovzond.mgis2.capital_constructs;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 * <p/>
 * Capital construct type
 */
@Entity
@Table(name = "occ_construction_type")
public class ConstructionType implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_occ_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(nullable = false, unique = true)
	private String code;

	@Column(nullable = false, unique = true)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ConstructionType clone() {
		ConstructionType type = new ConstructionType();
		type.setCode(code);
		type.setId(id);
		type.setName(name);
		return type;
	}
}
