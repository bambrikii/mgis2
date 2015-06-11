package ru.sovzond.mgis2.registers.oks.coord_system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Alexander Arakelyan
 *
 *         Перечень систем координат
 */
@Entity
@Table(name = "rosreg_oks_coord_system")
public class CoordSystem {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Integer id;

	/**
	 * Наименование системы координат
	 */
	@Column
	private String name;

	/**
	 * Код системы координат, на который ссылаются пространственные объекты
	 * (EntitySpatial)
	 */
	@Column
	private String csid;

}
