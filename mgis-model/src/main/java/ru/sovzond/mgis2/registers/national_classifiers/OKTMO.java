package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/**
 * ОКТМО
 */
@Entity
@Table(name = "nc_oktmo", indexes = {@Index(columnList = "name", name = "nc_oktmo_name_index")})
public class OKTMO implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_oktmo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column
	private String name;

	@ManyToOne
	private OKTMO parent;

	@Column(name = "control_number")
	private int controlNumber;

	@Column
	private String comment;

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

	public OKTMO getParent() {
		return parent;
	}

	public void setParent(OKTMO parent) {
		this.parent = parent;
	}

	public int getControlNumber() {
		return controlNumber;
	}

	public void setControlNumber(int controlNumber) {
		this.controlNumber = controlNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public OKTMO clone() {
		OKTMO oktmo = new OKTMO();
		oktmo.setId(id);
		oktmo.setCode(code);
		oktmo.setControlNumber(controlNumber);
		oktmo.setName(name);
		oktmo.setParent(parent != null ? parent.clone() : null);

		return oktmo;
	}
}
