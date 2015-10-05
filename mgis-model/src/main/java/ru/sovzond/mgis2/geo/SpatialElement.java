package ru.sovzond.mgis2.geo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 05.10.15.
 */
@Entity
@Table(name = "mgis_geo_spatial_elem", indexes = {@Index(name = "mgis_geo_spatial_elem_sg_pos_ux", columnList = "position, spatial_group_id")})
public class SpatialElement implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_geo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private BigDecimal position;

	@OneToMany(mappedBy = "spatialElement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Coordinate> coordinates = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "spatial_group_id")
	private SpatialGroup spatialGroup;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPosition() {
		return position;
	}

	public void setPosition(BigDecimal position) {
		this.position = position;
	}

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public SpatialGroup getSpatialGroup() {
		return spatialGroup;
	}

	public void setSpatialGroup(SpatialGroup spatialGroup) {
		this.spatialGroup = spatialGroup;
	}

	public SpatialElement clone() {
		SpatialElement spatialElement = new SpatialElement();
		spatialElement.setId(id);
		spatialElement.setPosition(position);
		spatialElement.setCoordinates(coordinates.stream().map(Coordinate::clone).collect(Collectors.toList()));
		return spatialElement;
	}

}
