package ru.sovzond.mgis2.indicators;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alexander Arakelyan on 06/11/15.
 * <p>
 * Технические показатели
 */
@Entity
@Table(name = "mgis2_tech_indicator")
public class TechnicalIndicator extends Indicator {
	public TechnicalIndicator clone() {
		TechnicalIndicator technicalIndicator = new TechnicalIndicator();
		technicalIndicator.setId(getId());
		technicalIndicator.setName(getName());
		technicalIndicator.setUnitOfMeasure(getUnitOfMeasure() != null ? getUnitOfMeasure().clone() : null);
		return technicalIndicator;
	}
}
