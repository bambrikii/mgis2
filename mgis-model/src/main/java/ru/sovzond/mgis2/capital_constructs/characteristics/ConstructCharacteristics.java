package ru.sovzond.mgis2.capital_constructs.characteristics;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
}
