package ru.sovzond.mgis2.capital_constructs.characteristics;

import ru.sovzond.mgis2.registers.national_classifiers.OKEI;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Alexander Arakelyan on 06.11.15.
 */
@Entity
@Table(name = "oks_capital_construct_economic_characteristic")
public class ConstructEconomicCharacteristic implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Вид стоимости
	 */
	private PriceType priceType;
	/**
	 * Значение
	 */
	private String value;
	/**
	 * Единица измерения
	 */
	private OKEI unitOfMeasure;

	/**
	 * Дата установления значения
	 */
	private Date valueDate;

	private OKOF

	/**
	 * Амортизационная группа
	 */
	@Enumerated(EnumType.STRING)
	private AmortizationGroup amortizationGroup;


}
