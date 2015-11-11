package ru.sovzond.mgis2.indicators;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alexander Arakelyan on 11.11.15.
 */
@Entity
@Table(name = "mgis2_economic_indicator")
public class EconomicIndicator extends Indicator {
	public EconomicIndicator clone() {
		EconomicIndicator economicIndicator = new EconomicIndicator();
		economicIndicator.setId(getId());
		economicIndicator.setName(getName());
		economicIndicator.setUnitOfMeasure(getUnitOfMeasure() != null ? getUnitOfMeasure().clone() : null);
		return economicIndicator;
	}
}
