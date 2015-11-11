package ru.sovzond.mgis2.indicators;

import ru.sovzond.mgis2.registers.national_classifiers.OKEI;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 11.11.15.
 */
@Entity
@Table(name = "mgis2_indicator")
@Inheritance(strategy = InheritanceType.JOINED)
public class Indicator {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_indicator_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OKEI getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(OKEI unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Indicator clone() {
		Indicator indicator = new Indicator();
		indicator.setId(id);
		indicator.setName(name);
		indicator.setUnitOfMeasure(unitOfMeasure != null ? unitOfMeasure.clone() : null);
		return indicator;
	}
}
