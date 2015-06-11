package ru.sovzond.mgis2.registers.oks.contractors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Alexander Arakelyan
 *
 *         Кадастровый инженер
 */
@Entity
@Table(name = "rosreg_oks_engineer")
public class Engineer {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Integer id;

	/**
	 * Фамилия
	 */
	@Column
	private String familyName;

	/**
	 * Имя
	 */
	@Column
	private String firstName;

	/**
	 * Отчество
	 */
	@Column
	private String patronymic;

	/**
	 * Номер квалификационного аттестата кадастрового инженера
	 */
	@Column
	private String nCertificate;

	/**
	 * Наименование юридического лица
	 */
	@Column
	private String organizationName;
}
