package ru.sovzond.mgis2.kladr;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 08.09.15.
 */
@Entity
@Table(name = "kladr_socrbase", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"level", "socrname"})
})
public class KLADRShortName {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "kladr_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Уровень объекта данного типа
	 */
	@Column(name = "level")
	private String level;

	/**
	 * Сокращенное наименование типа объекта
	 */
	@Column(name = "scname")
	private String scname;

	/**
	 * Полное наименование типа объекта
	 */
	@Column(name = "socrname")
	private String socrname;

	/**
	 * Код типа объекта
	 */
	@Column(name = "kod_t_st")
	private String kod_t_st;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getScname() {
		return scname;
	}

	public void setScname(String scname) {
		this.scname = scname;
	}

	public String getSocrname() {
		return socrname;
	}

	public void setSocrname(String socrname) {
		this.socrname = socrname;
	}

	public String getKod_t_st() {
		return kod_t_st;
	}

	public void setKod_t_st(String kod_t_st) {
		this.kod_t_st = kod_t_st;
	}
}
