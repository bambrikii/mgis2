package ru.sovzond.mgis2.capital_constructs.characteristics.technical;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 06/11/15.
 * <p/>
 * TODO: справочник видов недвижимого имущества
 */
@Entity
@Table(name = "occ_technical_characteristic_object_type")
public class TechnicalCharacteristicObjectType implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_occ_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TechnicalCharacteristicObjectType clone() {
		TechnicalCharacteristicObjectType objectType = new TechnicalCharacteristicObjectType();
		objectType.setId(id);
		return objectType;
	}
}
