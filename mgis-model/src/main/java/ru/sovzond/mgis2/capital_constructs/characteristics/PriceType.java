package ru.sovzond.mgis2.capital_constructs.characteristics;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 06.11.15.
 */
@Entity
@Table(name = "mgis_price_type")
public class PriceType implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

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

	public PriceType clone() {
		PriceType priceType = new PriceType();
		priceType.setId(id);
		priceType.setName(name);
		return priceType;
	}
}
