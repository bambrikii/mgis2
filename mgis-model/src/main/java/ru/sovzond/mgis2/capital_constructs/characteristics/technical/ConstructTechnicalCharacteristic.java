package ru.sovzond.mgis2.capital_constructs.characteristics.technical;

import ru.sovzond.mgis2.registers.national_classifiers.OKEI;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 06/11/15.
 */
@Entity
@Table(name = "oks_capital_construct_technical_characteristic")
public class ConstructTechnicalCharacteristic implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Вид объекта
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "object_type_id")
	private ConstructCharacteristicObjectType objectType;

	/**
	 * Технический показатель
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "technical_indicator_id")
	private TechnicalIndicator technicalIndicator;

	/**
	 * Значение
	 */
	@Column
	private String value;

	/**
	 * Единица измерения
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "okei_id")
	private OKEI unitOfMeasure;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConstructCharacteristicObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(ConstructCharacteristicObjectType objectType) {
		this.objectType = objectType;
	}

	public TechnicalIndicator getTechnicalIndicator() {
		return technicalIndicator;
	}

	public void setTechnicalIndicator(TechnicalIndicator technicalIndicator) {
		this.technicalIndicator = technicalIndicator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public OKEI getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(OKEI unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public ConstructTechnicalCharacteristic clone() {
		ConstructTechnicalCharacteristic characteristic = new ConstructTechnicalCharacteristic();
		characteristic.setId(id);
		characteristic.setObjectType(objectType != null ? objectType.clone() : null);
		characteristic.setTechnicalIndicator(technicalIndicator != null ? technicalIndicator.clone() : null);
		characteristic.setValue(value);
		characteristic.setUnitOfMeasure(unitOfMeasure != null ? unitOfMeasure.clone() : null);
		return characteristic;
	}
}
