package ru.sovzond.mgis2.registers.oks.contractors;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Alexander Arakelyan
 *
 *         сведения о кадастровых инженерах
 *
 */
@Entity
@Table(name = "rosreg_oks_uncompleted")
public class Contractor {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Кадастровый инженер
	 */
	@ManyToOne
	private Engineer engineer;

	/**
	 * Дата проведения кадастровых работ
	 */
	@Column
	private Date date;
}
