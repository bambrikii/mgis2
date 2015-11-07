package ru.sovzond.mgis2.capital_constructs.characteristics.economical;

import ru.sovzond.mgis2.registers.national_classifiers.OKEI;
import ru.sovzond.mgis2.registers.national_classifiers.OKOF;

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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "price_type_id")
	private PriceType priceType;

	/**
	 * Значение
	 */
	private String value;

	/**
	 * Единица измерения
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "okei_id")
	private OKEI unitOfMeasure;

	/**
	 * Дата установления значения
	 */
	private Date valueDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "okof_id")
	private OKOF okof;

	/**
	 * Амортизационная группа
	 */
	@Enumerated(EnumType.STRING)
	private AmortizationGroup amortizationGroup;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

	public OKEI getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(OKEI unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public OKOF getOkof() {
		return okof;
	}

	public void setOkof(OKOF okof) {
		this.okof = okof;
	}

	public AmortizationGroup getAmortizationGroup() {
		return amortizationGroup;
	}

	public void setAmortizationGroup(AmortizationGroup amortizationGroup) {
		this.amortizationGroup = amortizationGroup;
	}

	public ConstructEconomicCharacteristic clone() {
		ConstructEconomicCharacteristic characteristic = new ConstructEconomicCharacteristic();
		characteristic.setId(id);
		characteristic.setUnitOfMeasure(unitOfMeasure != null ? unitOfMeasure.clone() : null);
		characteristic.setAmortizationGroup(amortizationGroup);
		characteristic.setOkof(okof != null ? okof.clone() : null);
		characteristic.setPriceType(priceType != null ? priceType.clone() : null);
		return characteristic;
	}
}
