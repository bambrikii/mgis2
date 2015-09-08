package ru.sovzond.mgis2.kladr;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 08.09.15.
 */
@Entity
@Table(name = "kladr_socrbase")
public class Socrbase {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "kladr_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;
	@Column(name = "level")
	private String level;
	@Column(name = "socrname")
	private String socrname;
	@Column(name = "kod_t_st")
	private String kod_t_st;
}
