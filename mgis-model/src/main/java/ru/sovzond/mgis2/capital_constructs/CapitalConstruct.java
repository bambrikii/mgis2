package ru.sovzond.mgis2.capital_constructs;

import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.kladr.KLADRLocality;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 */
@Entity
@Table(name = "mgis2_oks_capital_construct")
public class CapitalConstruct implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	private String name;

	private ConstructType type;

	private String objectPurpose;

	/*
	 * Общие сведения
	 */

	private String cadastralNumber;

	/**
	 * Условный номер
	 */
	private String conditionalNumber;

	/**
	 * Инвентарный номер
	 */
	private String inventoryNumber;

	/**
	 * Общая площадь
	 */
	private Double overalArea;

	/**
	 * Муниципальное образование
	 */
	private KLADRLocality municipalEntity;

	/**
	 * Адрес
	 */
	private Address address;

	/**
	 * Местоположение
	 */
	private String placement;

	/**
	 * Литера
	 */
	private String letter;

	/**
	 * Дата постановки на технический учёт
	 */
	private Date technicalAccouningStatementDate;

	/**
	 * Фактическое использование
	 */
	private String actualUsage;

	/**
	 * Год ввода объекта в эксплуатацию
	 */
	private Integer operationStartYear;

	/**
	 *
	 */
	private Integer buildCompletionYear;


	public CapitalConstruct clone() {
		CapitalConstruct construct = new CapitalConstruct();
		return construct;
	}
}
