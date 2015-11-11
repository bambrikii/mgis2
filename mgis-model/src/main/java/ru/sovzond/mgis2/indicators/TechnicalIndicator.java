package ru.sovzond.mgis2.indicators;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alexander Arakelyan on 06/11/15.
 * <p/>
 * Технические показатели
 */
@Entity
@Table(name = "oks_tech_indicator")
public class TechnicalIndicator extends Indicator {
	public TechnicalIndicator clone() {
		TechnicalIndicator indicator = new TechnicalIndicator();
		indicator.setId(getId());
		indicator.setName(getName());
		return indicator;
	}
}
