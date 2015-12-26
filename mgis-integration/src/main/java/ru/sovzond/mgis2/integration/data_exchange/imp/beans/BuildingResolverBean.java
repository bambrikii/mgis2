package ru.sovzond.mgis2.integration.data_exchange.imp.beans;

import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.BuildingDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.IncompleteDTO;

/**
 * Created by Alexander Arakelyan on 25/12/15.
 */
@Service
public class BuildingResolverBean {
	public CapitalConstruction resolve(BuildingDTO obj) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public CapitalConstruction resolve(IncompleteDTO obj) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public void updateCoordinateSystem(Long id, CoordinateSystemDTO coordinateSystemDTO) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
