package ru.sovzond.mgis2.indicators;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alexander Arakelyan on 06.11.15.
 */
@Entity
@Table(name = "mgis2_price_indicator")
public class PriceIndicator extends EconomicIndicator {
	public PriceIndicator clone() {
		PriceIndicator priceIndicator = new PriceIndicator();
		priceIndicator.setId(getId());
		priceIndicator.setName(getName());
		priceIndicator.setUnitOfMeasure(getUnitOfMeasure() != null ? getUnitOfMeasure().clone() : null);
		return priceIndicator;
	}
}
