package ru.sovzond.mgis2.geo.layers;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 09.10.15.
 */
@Entity
@Table(name = "mgis_geo_layer")
public class Layer {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_geo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	@Column
	private boolean singleSelect;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Layer parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Layer> children = new ArrayList<>();

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

	public boolean isSingleSelect() {
		return singleSelect;
	}

	public void setSingleSelect(boolean singleSelect) {
		this.singleSelect = singleSelect;
	}

	public Layer getParent() {
		return parent;
	}

	public void setParent(Layer parent) {
		this.parent = parent;
	}

	public List<Layer> getChildren() {
		return children;
	}

	public void setChildren(List<Layer> children) {
		this.children = children;
	}
}
