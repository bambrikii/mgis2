package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 11.09.15.
 */
@Entity
@Table(name = "nc_okopf")
public class OKOPF implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_okopf_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * X XX XX
	 * /\ /\ /\
	 * │   │  │
	 * └───┼──┼─── раздел классификатора
	 * └───┼─── тип организационно-правовой формы
	 * └──── вид организационно-правовой формы
	 */
	@Column
	private String code;

	@Column
	private String name;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OKOPF clone() {
		OKOPF okopf = new OKOPF();
		okopf.setId(id);
		okopf.setCode(code);
		okopf.setName(name);
		return okopf;
	}
}
