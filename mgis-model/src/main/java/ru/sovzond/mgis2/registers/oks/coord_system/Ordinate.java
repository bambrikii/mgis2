package ru.sovzond.mgis2.registers.oks.coord_system;

import java.math.BigDecimal;
import java.math.BigInteger;

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
 *         Координата
 */
@Entity
@Table(name = "rosreg_oks_coord_ordinate")
public class Ordinate {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Примечание
	 */
	@Column
	private String note;

	/**
	 * Координата X
	 */
	@Column
	private BigDecimal x;

	/**
	 * Координата Y
	 */
	@Column
	private BigDecimal y;

	/**
	 * Номер точки (порядок обхода)
	 */
	@Column
	private BigInteger ordNmb;

	/**
	 * Номер точки (межевой точки)
	 */
	@Column
	private BigInteger numGeopoint;

	/**
	 * Средняя квадратическая погрешность положения характерной точки
	 */
	@Column
	private BigDecimal deltaGeopoint;

}
