package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 11.09.15.
 */
@Entity
@Table(name = "nc_okved", indexes = {@Index(columnList = "code", name = "nc_okved_code_index")})
public class OKVED {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_okved_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String section;

	@Column(unique = true)
	private String code;

	@Column(length=1000)
	private String name;
}
