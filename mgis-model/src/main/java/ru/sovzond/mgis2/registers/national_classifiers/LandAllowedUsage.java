package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

@Entity
@Table(name = "nc_land_allowed_usage")
public class LandAllowedUsage implements Cloneable {

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

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private LandAllowedUsage parent;

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

	public LandAllowedUsage getParent() {
		return parent;
	}

	public void setParent(LandAllowedUsage parent) {
		this.parent = parent;
	}

	public LandAllowedUsage clone() {
		LandAllowedUsage usage = new LandAllowedUsage();
		usage.setId(id);
		usage.setNumber(number);
		usage.setClassificationCode(classificationCode);
		usage.setName(name);
		if (parent != null) {
			LandAllowedUsage parent2 = new LandAllowedUsage();
			parent2.setId(parent.getId());
			parent2.setClassificationCode(parent.getClassificationCode());
			parent2.setNumber(parent.getNumber());
			parent2.setName(parent.getName());
			usage.setParent(parent2);
		}
		return usage;
	}

}
