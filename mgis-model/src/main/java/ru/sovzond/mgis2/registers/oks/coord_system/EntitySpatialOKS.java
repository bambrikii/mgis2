package ru.sovzond.mgis2.registers.oks.coord_system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Alexander Arakelyan
 *
 *         Описание местоположения границ
 */
@Entity
@Table(name = "rosreg_oks_coord_entity_spatial")
public class EntitySpatialOKS {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Элемент контура
	 */
	@ElementCollection
	private List<SpatialElementOKS> spatialElement = new ArrayList<SpatialElementOKS>();

	/**
	 * Ссылка на систему координат
	 */
	@ManyToOne
	private CoordSystem entSys;
}
