package ru.sovzond.mgis2.registers.oks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ru.sovzond.mgis2.registers.oks.buildings.Building;
import ru.sovzond.mgis2.registers.oks.constructions.Construction;
import ru.sovzond.mgis2.registers.oks.flats.Flat;

@Entity
@Table(name = "rosreg_oks_realty")
public class Realty {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "rosreg_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne
	private Building building;

	@ManyToOne
	private Construction construction;

	@ManyToOne
	private Uncompleted uncompleted;

	@ManyToOne
	private Flat flat;

}
