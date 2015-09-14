package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 11.09.15.
 */
@Entity
@Table(name = "nc_okogu", indexes = {@Index(columnList = "code", name = "nc_okogu_code_index"), @Index(columnList = "name", name = "nc_okogu_name_index")})
public class OKOGU implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_okogu_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column(length = 1000)
	private String name;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public OKOGU clone() {
		OKOGU okogu = new OKOGU();
		okogu.setId(id);
		okogu.setCode(code);
		okogu.setName(name);
		okogu.setComment(comment);
		return okogu;
	}
}
