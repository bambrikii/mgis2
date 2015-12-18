package ru.sovzond.mgis2.registers.persons;

import javax.persistence.*;

/**
 * Created by donchenko-y on 12/17/15.
 */

@Entity
@Table(name = "mgis2_natural_person_cert_types")
public class NaturalPersonCertificateType implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_person_cert_type_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column
	private String abbreviation;

	@Column(name = "series_pattern")
	private String seriesPattern;

	@Column(name = "note", columnDefinition = "text")
	private String note;

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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getSeriesPattern() {
		return seriesPattern;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setSeriesPattern(String seriesPattern) {
		this.seriesPattern = seriesPattern;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public NaturalPersonCertificateType clone() {
		NaturalPersonCertificateType naturalPersonCertificateType = new NaturalPersonCertificateType();
		naturalPersonCertificateType.setId(id);
		naturalPersonCertificateType.setCode(code);
		naturalPersonCertificateType.setName(name);
		naturalPersonCertificateType.setAbbreviation(abbreviation);
		naturalPersonCertificateType.setSeriesPattern(seriesPattern);
		naturalPersonCertificateType.setNote(note);
		return naturalPersonCertificateType;
	}
}
