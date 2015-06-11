package ru.sovzond.mgis2.registers.oks.flats;

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
 *         Назначение и вид помещения
 */
@Entity
@Table(name = "rosreg_oks_flat_assignation")
public class AssignationFlat {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Назначение помещения
	 *
	 * По справочнику dAssFlat «Назначение помещений» Значения: 206001000000
	 * (Нежилое помещение); 206002000000 (Жилое помещение)
	 */
	private String assignationCode;

	/**
	 * Вид жилого помещения
	 *
	 * По справочнику dAssFlatType «Вид жилого помещения» Значения: 205001000000
	 * (Квартира); 205002000000 (Комната). Указывается в отношении жилого
	 * помещения, расположенного в жилом (в том числе многоквартирном) доме
	 * (если AssignationCode 206002000000) Справочник dAssFlatType.
	 */
	private String assignationType;

	/**
	 * Нежилое помещение, являющееся общим имуществом в многоквартирном доме
	 * (True - да)
	 *
	 * Указывается для нежилых помещений, составляющих общее имущество в
	 * многоквартирном доме (если AssignationCode 206001000000).
	 */
	private Boolean totalAssets;

}
