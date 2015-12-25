package ru.sovzond.mgis2.integration.data_exchange.imp.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
public class OrdinateDTO {

	public double x;
	public double y;
	public int ordNumber;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getOrdNumber() {
		return ordNumber;
	}

	public void setOrdNumber(int ordNumber) {
		this.ordNumber = ordNumber;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("x", x).append("y", y).append("ordNumber", ordNumber).toString();
	}

}
