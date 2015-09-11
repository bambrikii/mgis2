package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/**
 * Форма собственности (ОКФС)
 */
@Entity
@Table(name = "nc_okfs")
public class OKFS implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_okfs_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;


	@Column
	private String name;

	@Column(name = "gathering_algo")
	private String gatheringAlgo;

	@ManyToOne
	@JoinColumn(name = "gathering_parent_id")
	private OKFS gatheringParent;

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

	public String getGatheringAlgo() {
		return gatheringAlgo;
	}

	public void setGatheringAlgo(String gatheringAlgo) {
		this.gatheringAlgo = gatheringAlgo;
	}

	public OKFS getGatheringParent() {
		return gatheringParent;
	}

	public void setGatheringParent(OKFS gatheringParent) {
		this.gatheringParent = gatheringParent;
	}

	public OKFS clone() {
		OKFS form = new OKFS();
		form.setId(id);
		form.setCode(code);
		form.setName(name);
		form.setGatheringAlgo(gatheringAlgo);
		form.setGatheringParent(gatheringParent != null ? gatheringParent.clone() : null);
		return form;
	}

}
