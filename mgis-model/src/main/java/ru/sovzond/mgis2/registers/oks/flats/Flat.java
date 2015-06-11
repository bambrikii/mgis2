package ru.sovzond.mgis2.registers.oks.flats;

import java.math.BigDecimal;
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
 *         Помещение
 */
@Entity
@Table(name = "rosreg_oks_flat")
public class Flat {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Integer id;

	@ElementCollection
	private List<String> cadastralBlocks = new ArrayList<String>();

	@Column
	private String objectType;

	@ManyToOne
	private AssignationFlat assignation;

	@Column
	private BigDecimal area;

	@ManyToOne
	private Address address;

	@Column
	private String cadastralNumber;

}
