package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.geo.SpatialGroup;
import ru.sovzond.mgis2.lands.Land;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */

public class LandTargetDecorator implements TargetDecorator<Land> {
	private Land land;

	public LandTargetDecorator wrap(Land land) {
		this.land = land;
		return this;
	}

	@Override
	public Long getId() {
		return land.getId();
	}

	@Override
	public void setSpatialData(SpatialGroup spatialGroup) {
		land.setSpatialData(spatialGroup);
	}
}
