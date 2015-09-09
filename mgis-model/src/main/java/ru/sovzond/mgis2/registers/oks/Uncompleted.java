package ru.sovzond.mgis2.registers.oks;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Arakelyan
 *
 *         Объект незавершенного строительства
 */
@Entity
@Table(name = "rosreg_oks_uncompleted")
public class Uncompleted {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Номер (номера) кадастрового квартала (кадастровых кварталов), в пределах
	 * которого (которых) расположен данный объект недвижимости
	 */
	@ElementCollection
	private List<String> cadastralBlocks = new ArrayList<String>();

	/**
	 * Вид объекта недвижимости - Объект незавершенного строительства
	 */
	@Column
	private String objectType;

	/**
	 * Адрес (описание местоположения)
	 */
	@ManyToOne
	private OKSAddress address;

	/**
	 * Кадастровый номер
	 */
	@Column
	private String cadastralNumber;
}
