package ru.sovzond.mgis2.registers.oks;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
	private OKSAddress address;

	/**
	 * Кадастровый номер
	 */
	@Column
	private String cadastralNumber;
}
