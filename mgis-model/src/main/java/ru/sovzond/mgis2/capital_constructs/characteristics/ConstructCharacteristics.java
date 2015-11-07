package ru.sovzond.mgis2.capital_constructs.characteristics;

import ru.sovzond.mgis2.capital_constructs.characteristics.economical.ConstructEconomicCharacteristic;
import ru.sovzond.mgis2.capital_constructs.characteristics.technical.ConstructTechnicalCharacteristic;

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

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "oks_capital_construct_economic_characteristics")
	private List<ConstructEconomicCharacteristic> economicCharacteristics = new ArrayList<>();

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "oks_capital_construct_technical_characteristics")
	private List<ConstructTechnicalCharacteristic> technicalCharacteristics = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ConstructEconomicCharacteristic> getEconomicCharacteristics() {
		return economicCharacteristics;
	}

	public void setEconomicCharacteristics(List<ConstructEconomicCharacteristic> economicCharacteristics) {
		this.economicCharacteristics = economicCharacteristics;
	}

	public List<ConstructTechnicalCharacteristic> getTechnicalCharacteristics() {
		return technicalCharacteristics;
	}

	public void setTechnicalCharacteristics(List<ConstructTechnicalCharacteristic> technicalCharacteristics) {
		this.technicalCharacteristics = technicalCharacteristics;
	}

	public ConstructCharacteristics clone() {
		ConstructCharacteristics characteristics = new ConstructCharacteristics();
		characteristics.setId(id);
		characteristics.getEconomicCharacteristics().addAll(economicCharacteristics.stream().map(ConstructEconomicCharacteristic::clone).collect(Collectors.toList()));
		characteristics.getTechnicalCharacteristics().addAll(technicalCharacteristics.stream().map(ConstructTechnicalCharacteristic::clone).collect(Collectors.toList()));
		return characteristics;
	}
}
