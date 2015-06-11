package ru.sovzond.mgis2.registers.oks.coord_system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Alexander Arakelyan
 *
 *         Элемент контура
 */
@Entity
@Table(name = "rosreg_oks_coord_spatial_element")
public class SpatialElementOKS {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Часть элемента (точка, окружность)
	 */
	@ElementCollection
	private List<SpelementUnitOKS> spelementUnit = new ArrayList<>();

	/**
	 * Номер контура
	 */
	@Column
	private Integer number;
}
