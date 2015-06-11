package ru.sovzond.mgis2.registers.oks.coord_system;

import java.math.BigDecimal;
import java.math.BigInteger;

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
 *         Часть элемента (точка, окружность)
 */
@Entity
@Table(name = "rosreg_oks_coord_spelement_unit")
public class SpelementUnitOKS {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Integer id;

	/**
	 * Координата
	 */
	@ManyToOne
	private Ordinate ordinate;

	/**
	 * Радиус
	 */
	@Column
	private BigDecimal r;

	/**
	 * Элементарный тип для части элемента
	 */
	@Column
	private TypeUnit typeUnit;

	/**
	 * Номер части элемента (порядок обхода)
	 */
	@Column
	private BigInteger suNumb;
}
