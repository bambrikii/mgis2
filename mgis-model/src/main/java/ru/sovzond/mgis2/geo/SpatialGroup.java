package ru.sovzond.mgis2.geo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 05.10.15.
 */
@Entity
@Table(name = "mgis_geo_spatial_group")
public class SpatialGroup implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_geo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "coord_system_id")
	private CoordinateSystem coordinateSystem;

	@OneToMany(mappedBy = "spatialGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SpatialElement> spatialElements = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CoordinateSystem getCoordinateSystem() {
		return coordinateSystem;
	}

	public void setCoordinateSystem(CoordinateSystem coordinateSystem) {
		this.coordinateSystem = coordinateSystem;
	}

	public List<SpatialElement> getSpatialElements() {
		return spatialElements;
	}

	public void setSpatialElements(List<SpatialElement> spatialElements) {
		this.spatialElements = spatialElements;
	}

	public SpatialGroup clone() {
		SpatialGroup spatialGroup = new SpatialGroup();
		spatialGroup.setId(id);
		spatialGroup.setCoordinateSystem(coordinateSystem != null ? coordinateSystem.clone() : null);
		spatialGroup.setSpatialElements(spatialElements.stream().map(SpatialElement::clone).collect(Collectors.toList()));
		return spatialGroup;
	}
}
