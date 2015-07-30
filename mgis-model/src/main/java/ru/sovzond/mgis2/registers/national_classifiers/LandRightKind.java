package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 */
@Entity
@Table(name = "nc_land_right_kind")
public class LandRightKind implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_land_right_kind_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String number;

	@Column(name = "classification_code")
	private String classificationCode;

	@Column
	private String name;

	@Column
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LandRightKind clone() {
		LandRightKind kind = new LandRightKind();
		kind.setId(id);
		kind.setNumber(number);
		kind.setClassificationCode(classificationCode);
		kind.setName(name);
		kind.setComment(comment);
		return kind;
	}


}
