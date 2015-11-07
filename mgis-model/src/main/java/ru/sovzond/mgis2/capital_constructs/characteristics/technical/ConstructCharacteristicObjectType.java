package ru.sovzond.mgis2.capital_constructs.characteristics.technical;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 06/11/15.
 */
@Entity
@Table(name = "oks_capital_construct_tech_char_obj_type")
public class ConstructCharacteristicObjectType implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_oks_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConstructCharacteristicObjectType clone() {
		ConstructCharacteristicObjectType objectType = new ConstructCharacteristicObjectType();
		objectType.setId(id);
		return objectType;
	}
}
