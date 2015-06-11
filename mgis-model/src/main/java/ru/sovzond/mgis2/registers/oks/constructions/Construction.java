package ru.sovzond.mgis2.registers.oks.constructions;

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

import ru.sovzond.mgis2.registers.oks.Address;

/**
 * @author Alexander Arakelyan
 *
 *         Сооружение
 *
 */
@Entity
@Table(name = "rosreg_oks_construction")
public class Construction {

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
	 * Вид объекта недвижимости - Сооружение
	 */
	@Column
	private String objectType;

	/**
	 * Назначение сооружения
	 */
	@Column
	private String assignationName;

	/**
	 * Адрес (описание местоположения)
	 */
	@ManyToOne
	private Address address;

	/**
	 * Кадастровый номер
	 */
	@Column
	private String cadastralNumber;
}
