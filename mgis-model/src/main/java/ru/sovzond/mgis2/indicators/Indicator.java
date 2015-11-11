package ru.sovzond.mgis2.indicators;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 11.11.15.
 */
@Entity
@Table(name = "mgis_indicator")
@Inheritance(strategy = InheritanceType.JOINED)
public class Indicator {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_indicator_seq", allocationSize = 1)
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

	public Indicator clone() {
		Indicator indicator = new Indicator();
		indicator.setId(id);
		indicator.setName(name);
		return indicator;
	}
}
