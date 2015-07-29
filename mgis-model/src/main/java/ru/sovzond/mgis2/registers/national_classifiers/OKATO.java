package ru.sovzond.mgis2.registers.national_classifiers;

/**
 * Created by Alexander Arakelyan on 21.07.15.
 */

import javax.persistence.*;

/**
 * Субъект РФ
 */
@Entity
@Table(name = "nc_okato")
public class OKATO implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "national_classifiers_okato_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column
	private String name;

	@Column(name = "control_number")
	private Integer controlNumber;

	@Column
	private Integer nodeCount;

	@Column(name = "parent_code")
	private String parentCode;

	@Column(name = "node_count")
	private Integer node_count;

	@ManyToOne
	private OKATO parent;

	@Column(name = "additional_info")
	private String additionalInfo;

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

	public Integer getControlNumber() {
		return controlNumber;
	}

	public void setControlNumber(Integer controlNumber) {
		this.controlNumber = controlNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(Integer nodeCount) {
		this.nodeCount = nodeCount;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public OKATO getParent() {
		return parent;
	}

	public void setParent(OKATO parent) {
		this.parent = parent;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getNode_count() {
		return node_count;
	}

	public void setNode_count(Integer node_count) {
		this.node_count = node_count;
	}

	public OKATO clone() {
		OKATO okato = new OKATO();
		okato.setId(id);
		okato.setCode(code);
		okato.setName(name);
		okato.setControlNumber(controlNumber);
		okato.setParent(okato.parent != null ? okato.parent.clone() : null);
		return okato;
	}
}
