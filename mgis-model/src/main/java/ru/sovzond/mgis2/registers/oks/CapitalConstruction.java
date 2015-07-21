package ru.sovzond.mgis2.registers.oks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rosreg_oks_capital_construction")
public class CapitalConstruction {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Номер (номера) кадастрового квартала (кадастровых кварталов), в пределах которого (которых) расположен данный объект недвижимости
	 */
	@ElementCollection
	private List<String> cadastralBocks = new ArrayList<String>();

	/**
	 * Вид объекта недвижимости - Здание
	 */
	@Column
	private String objectType;

	/**
	 * Назначение здания
	 */
	@Column
	private String assignationBuilding;

	/**
	 * Площадь в квадратных метрах
	 */
	@Column
	private BigDecimal area;

	/**
	 * Адрес (описание местоположения)
	 */
	@ManyToOne
	private Address address;

	/**
	 * Кадастровый номер
	 */
	@Column
	private String cadastralNumber;
}
