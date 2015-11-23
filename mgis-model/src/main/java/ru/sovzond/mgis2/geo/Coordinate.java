package ru.sovzond.mgis2.geo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Alexander Arakelyan on 05.10.15.
 */
@Entity
@Table(name = "mgis2_geo_coord")
public class Coordinate implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_geo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(name = "position")
	private BigInteger position;

	@Column
	private BigDecimal x;

	@Column
	private BigDecimal y;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getPosition() {
		return position;
	}

	public void setPosition(BigInteger position) {
		this.position = position;
	}

	public BigDecimal getX() {
		return x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	public Coordinate clone() {
		Coordinate coordinate = new Coordinate();
		coordinate.setId(id);
		coordinate.setPosition(position);
		coordinate.setX(x);
		coordinate.setY(y);
		return coordinate;
	}
}
