package ru.sovzond.mgis2.registers.oks;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rosreg_oks_certification_doc")
public class CertificationDoc {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Integer id;

	/**
	 * Наименование органа кадастрового учета
	 */
	@Column
	private String organization;

	/**
	 * Дата
	 */
	@Column
	private Date date;

	/**
	 * Номер документа
	 */
	@Column
	private String number;

}
