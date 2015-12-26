package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.geo.SpatialGroup;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */
public class ConstructionTargetDecorator implements TargetDecorator<CapitalConstruction> {
	private CapitalConstruction capitalConstruction;

	@Override
	public ConstructionTargetDecorator wrap(CapitalConstruction capitalConstruction) {
		this.capitalConstruction = capitalConstruction;
		return null;
	}

	@Override
	public Long getId() {
		return capitalConstruction.getId();
	}

	@Override
	public void setSpatialData(SpatialGroup spatialGroup) {
		capitalConstruction.setSpatialData(spatialGroup);
	}
}
