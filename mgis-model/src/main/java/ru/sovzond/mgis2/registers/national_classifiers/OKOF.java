package ru.sovzond.mgis2.registers.national_classifiers;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 06.11.15.
 */
@Entity
@Table(name = "nc_okof")
public class OKOF implements Cloneable {
	/**
	 *
	 */
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "nc_okof_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(name = "original_id", unique = true)
	private Long originalId;

	@Column(name = "name")
	private String name;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Column(name = "control_number")
	private String controlNumber;

	@ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = OKOF.class)
	@JoinColumns(@JoinColumn(name = "parent_id", nullable = true))
	private OKOF parent;

	@Column(name = "parent_code")
	private String parentCode;

	@Column(name = "node_count")
	private Integer nodeCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOriginalId() {
		return originalId;
	}

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getControlNumber() {
		return controlNumber;
	}

	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}

	public OKOF getParent() {
		return parent;
	}

	public void setParent(OKOF parent) {
		this.parent = parent;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(Integer nodeCount) {
		this.nodeCount = nodeCount;
	}

	public OKOF clone() {
		OKOF okof = new OKOF();
		okof.setControlNumber(controlNumber);
		okof.setId(id);
		okof.setName(name);
		okof.setNodeCount(nodeCount);
		okof.setOriginalId(originalId);
		if (parent != null) {
			OKOF parent2 = new OKOF();
			parent2.setId(parent2.getId());
			okof.setParent(parent2);
		}
		return okof;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
