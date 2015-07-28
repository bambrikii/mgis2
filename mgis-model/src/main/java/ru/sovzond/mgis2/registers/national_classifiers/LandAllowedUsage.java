package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

@Entity
@Table(name = "nc_land_allowed_usage")
public class LandAllowedUsage {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_land_allowed_usage_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String number;

	@Column(name = "classification_code", unique = true, nullable = false)
	private String classificationCode;

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getClassificationCode() {
		return classificationCode;
	}

	public void setClassificationCode(String classificationCode) {
		this.classificationCode = classificationCode;
	}
}
