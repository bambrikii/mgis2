package ru.sovzond.mgis2.capital_constructs.characteristics;

import ru.sovzond.mgis2.capital_constructs.characteristics.economical.EconomicCharacteristic;
import ru.sovzond.mgis2.capital_constructs.characteristics.technical.TechnicalCharacteristic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 06.11.15.
 */
@Entity
@Table(name = "oks_capital_construct_characteristics")
public class ConstructCharacteristics implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<EconomicCharacteristic> economicCharacteristics = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<TechnicalCharacteristic> technicalCharacteristics = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<EconomicCharacteristic> getEconomicCharacteristics() {
		return economicCharacteristics;
	}

	public void setEconomicCharacteristics(List<EconomicCharacteristic> economicCharacteristics) {
		this.economicCharacteristics = economicCharacteristics;
	}

	public List<TechnicalCharacteristic> getTechnicalCharacteristics() {
		return technicalCharacteristics;
	}

	public void setTechnicalCharacteristics(List<TechnicalCharacteristic> technicalCharacteristics) {
		this.technicalCharacteristics = technicalCharacteristics;
	}

	public ConstructCharacteristics clone() {
		ConstructCharacteristics characteristics = new ConstructCharacteristics();
		characteristics.setId(id);
		characteristics.getEconomicCharacteristics().addAll(economicCharacteristics.stream().map(EconomicCharacteristic::clone).collect(Collectors.toList()));
		characteristics.getTechnicalCharacteristics().addAll(technicalCharacteristics.stream().map(TechnicalCharacteristic::clone).collect(Collectors.toList()));
		return characteristics;
	}
}
