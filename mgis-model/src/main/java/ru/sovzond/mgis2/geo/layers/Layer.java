package ru.sovzond.mgis2.geo.layers;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 09.10.15.
 */
@Entity
@Table(name = "mgis2_geo_layer", indexes = {@Index(name = "mgis2_geo_layer_sort_order_ix", columnList = "sort_order")})
public class Layer implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_geo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "active", nullable = false)
	private boolean active = true;

	@Column(name = "selected_by_default", nullable = false, columnDefinition = "boolean default false")
	private boolean selectedByDefault;

	@Column(name = "open_by_default", nullable = false, columnDefinition = "boolean default true")
	private boolean openByDefault = true;

	@Column(name = "sort_order")
	private Long sortOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Layer parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("sort_order")
	private List<Layer> childLayers = new ArrayList<>();

	@Column(name = "select_type")
	@Enumerated(EnumType.STRING)
	private LayerSelectType selectType;

	@Column(name = "service_type")
	@Enumerated(EnumType.STRING)
	private LayerType serviceType;

	@ElementCollection(fetch = FetchType.LAZY)
	@MapKeyColumn(name = "key")
	@Column(name = "value")
	@CollectionTable(name = "mgis2_geo_layer_params", joinColumns = {@JoinColumn(name = "geo_layer_id")})
	private Map<String, String> params = new HashMap<>();

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Layer getParent() {
		return parent;
	}

	public void setParent(Layer parent) {
		this.parent = parent;
	}

	public List<Layer> getChildLayers() {
		return childLayers;
	}

	public void setChildLayers(List<Layer> children) {
		this.childLayers = children;
	}

	public LayerSelectType getSelectType() {
		return selectType;
	}

	public void setSelectType(LayerSelectType selectType) {
		this.selectType = selectType;
	}

	public LayerType getServiceType() {
		return serviceType;
	}

	public void setServiceType(LayerType serviceType) {
		this.serviceType = serviceType;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public boolean isSelectedByDefault() {
		return selectedByDefault;
	}

	public void setSelectedByDefault(boolean selectedByDefault) {
		this.selectedByDefault = selectedByDefault;
	}

	public boolean isOpenByDefault() {
		return openByDefault;
	}

	public void setOpenByDefault(boolean openByDefault) {
		this.openByDefault = openByDefault;
	}

	public Layer clone() {
		Layer layer = new Layer();
		layer.setId(id);
		layer.setName(name);
		layer.setActive(active);
		layer.setChildLayers(childLayers.stream().map(Layer::clone).collect(Collectors.toList()));
		layer.setCode(code);
		layer.setParams(params.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
		Layer parent2;
		if (parent != null) {
			parent2 = new Layer();
			parent2.setId(parent.getId());
		} else {
			parent2 = null;
		}
		layer.setParent(parent2);
		layer.setSelectType(selectType);
		layer.setServiceType(serviceType);
		layer.setSortOrder(sortOrder);
		layer.setSelectedByDefault(selectedByDefault);
		layer.setOpenByDefault(openByDefault);
		return layer;
	}
}
