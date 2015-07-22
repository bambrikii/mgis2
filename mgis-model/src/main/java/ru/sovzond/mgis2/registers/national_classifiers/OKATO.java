package ru.sovzond.mgis2.registers.national_classifiers;

/**
 * Created by Alexander Arakelyan on 21.07.15.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Субъект РФ
 */
@Entity
@Table(name = "nc_okato")
public class OKATO {

	public enum KeyNumber {

	}

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "national_classifiers_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column
	private Integer keyNumber;

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

	public Integer getKeyNumber() {
		return keyNumber;
	}

	public void setKeyNumber(Integer keyNumber) {
		this.keyNumber = keyNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
