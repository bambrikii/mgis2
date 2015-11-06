package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 06.11.15.
 */
@Entity
@Table(name = "nc_okei")
public class OKEI implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_okei_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private Integer code;

	@Column(name = "name")
	private String name;

	@Column(name = "short_name_national")
	private String shortNameNational;

	@Column(name = "literal_name_national")
	private String literalNameNational;

	@Column(name = "short_name_international")
	private String shortNameInternational;

	@Column(name = "literal_name_international")
	private String literalNameInternational;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortNameNational() {
		return shortNameNational;
	}

	public void setShortNameNational(String shortNameNational) {
		this.shortNameNational = shortNameNational;
	}

	public String getLiteralNameNational() {
		return literalNameNational;
	}

	public void setLiteralNameNational(String literalNameNational) {
		this.literalNameNational = literalNameNational;
	}

	public String getShortNameInternational() {
		return shortNameInternational;
	}

	public void setShortNameInternational(String shortNameInternational) {
		this.shortNameInternational = shortNameInternational;
	}

	public String getLiteralNameInternational() {
		return literalNameInternational;
	}

	public void setLiteralNameInternational(String literalNameInternational) {
		this.literalNameInternational = literalNameInternational;
	}

	public OKEI clone() {
		OKEI okei = new OKEI();
		okei.setId(id);
		okei.setCode(code);
		okei.setLiteralNameInternational(literalNameInternational);
		okei.setLiteralNameNational(literalNameNational);
		okei.setName(name);
		okei.setShortNameInternational(shortNameInternational);
		okei.setShortNameNational(shortNameNational);
		return okei;
	}


}
