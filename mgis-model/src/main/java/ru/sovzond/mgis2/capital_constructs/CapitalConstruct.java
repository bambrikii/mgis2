package ru.sovzond.mgis2.capital_constructs;

import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.kladr.KLADRLocality;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Alexander Arakelyan on 05.11.15.
 */
@Entity
@Table(name = "oks_capital_construct")
public class CapitalConstruct implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String name;

	@Enumerated(EnumType.STRING)
	private ConstructType type;

	@Column
	private String objectPurpose;

	/*
	 * Общие сведения
	 */

	@Column
	private String cadastralNumber;

	/**
	 * Условный номер
	 */
	@Column
	private String conditionalNumber;

	/**
	 * Инвентарный номер
	 */
	@Column
	private String inventoryNumber;

	/**
	 * Общая площадь
	 */
	@Column
	private Double overalArea;

	/**
	 * Муниципальное образование
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "municipal_entity_id")
	private KLADRLocality municipalEntity;

	/**
	 * Адрес
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_id")
	private Address address;

	/**
	 * Местоположение
	 */
	@Column
	private String placement;

	/**
	 * Литера
	 */
	@Column
	private String letter;

	/**
	 * Дата постановки на технический учёт
	 */
	@Column
	private Date technicalAccouningStatementDate;

	/**
	 * Фактическое использование
	 */
	@Column
	private String actualUsage;

	/**
	 * Год ввода объекта в эксплуатацию
	 */
	@Column
	private Integer operationStartYear;

	/**
	 * Года завершения строительства
	 */
	@Column
	private Integer buildCompletionYear;

	/*
	 * Дата последней реконструкции
	 */
	@Column
	private Date lastReconstructionDate;

	/*
	 * Год последнего кап.ремонта
	 */
	@Column
	private Integer rebuildingLastYear;


	public CapitalConstruct clone() {
		CapitalConstruct construct = new CapitalConstruct();
		return construct;
	}
}
