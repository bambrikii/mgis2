package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.EntitySpatialDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */

public class LandSourceDecorator implements SourceDecorator<LandDTO> {
	private LandDTO land;

	public LandSourceDecorator wrap(LandDTO land) {
		this.land = land;
		return this;
	}

	@Override
	public String getName() {
		return land.getCadastralNumber();
	}

	@Override
	public EntitySpatialDTO getEntitySpatial() {
		return land.getEntitySpatial();
	}
}
