package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.ConstructDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.EntitySpatialDTO;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */

public class ConstructSourceDecorator implements SourceDecorator<ConstructDTO> {
	private ConstructDTO buildingDTO;

	@Override
	public SourceDecorator wrap(ConstructDTO land) {
		this.buildingDTO = land;
		return this;
	}

	@Override
	public String getName() {
		return buildingDTO.getCadastralNumber();
	}

	@Override
	public EntitySpatialDTO getEntitySpatial() {
		return buildingDTO.getEntitySpatial();
	}
}
