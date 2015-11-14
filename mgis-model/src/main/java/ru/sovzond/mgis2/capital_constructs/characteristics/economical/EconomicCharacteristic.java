package ru.sovzond.mgis2.capital_constructs.characteristics.economical;

import ru.sovzond.mgis2.indicators.PriceIndicator;
import ru.sovzond.mgis2.registers.national_classifiers.OKOF;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Alexander Arakelyan on 06.11.15.
 */
@Entity
@Table(name = "oks_capital_construct_economic_characteristic")
public class EconomicCharacteristic implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Вид стоимости
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "price_indicator_id")
	private PriceIndicator priceIndicator;

	/**
	 * Значение
	 */
	private String value;

	/**
	 * Дата установления значения
	 */
	private Date valueImplementationDate;

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

	public PriceIndicator getPriceIndicator() {
		return priceIndicator;
	}

	public void setPriceIndicator(PriceIndicator priceIndicator) {
		this.priceIndicator = priceIndicator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getValueImplementationDate() {
		return valueImplementationDate;
	}

	public void setValueImplementationDate(Date valueImplementationDate) {
		this.valueImplementationDate = valueImplementationDate;
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

	public EconomicCharacteristic clone() {
		EconomicCharacteristic characteristic = new EconomicCharacteristic();
		characteristic.setId(id);
		characteristic.setAmortizationGroup(amortizationGroup);
		characteristic.setValue(value);
		characteristic.setValueImplementationDate(valueImplementationDate);
		characteristic.setOkof(okof != null ? okof.clone() : null);
		characteristic.setPriceIndicator(priceIndicator != null ? priceIndicator.clone() : null);
		return characteristic;
	}

}
