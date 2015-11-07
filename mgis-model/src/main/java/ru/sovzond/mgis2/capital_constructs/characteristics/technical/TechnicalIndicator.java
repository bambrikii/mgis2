package ru.sovzond.mgis2.capital_constructs.characteristics.technical;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 06/11/15.
 * <p/>
 * Технические показатели
 */
@Entity
@Table(name = "oks_tech_indicator")
public class TechnicalIndicator implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

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

	public TechnicalIndicator clone() {
		TechnicalIndicator indicator = new TechnicalIndicator();
		indicator.setId(id);
		indicator.setName(name);
		return indicator;
	}

}
