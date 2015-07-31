package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/*
 * Обременения
 */
@Entity
@Table(name = "nc_land_encumbrance")
public class LandEncumbrance implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_land_encumbrance_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String code;

	@Column(name = "classification_code")
	private String classificationCode;

	@Column
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

	public String getClassificationCode() {
		return classificationCode;
	}

	public void setClassificationCode(String classificationCode) {
		this.classificationCode = classificationCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LandEncumbrance clone() {
		LandEncumbrance enc = new LandEncumbrance();
		enc.setId(id);
		enc.setCode(code);
		enc.setClassificationCode(classificationCode);
		enc.setName(name);
		return enc;
	}

}
