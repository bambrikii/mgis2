package ru.sovzond.mgis2.integration.data_exchange.imp.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.capital_construct.CapitalConstructBean;
import ru.sovzond.mgis2.capital_construct.ConstructTypeBean;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.capital_constructs.ConstructionType;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.BuildingDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.ConstructDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.IncompleteDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.ConstructSourceDecorator;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.ConstructionTargetDecorator;

/**
 * Created by Alexander Arakelyan on 25/12/15.
 */
@Service
public class BuildingResolverBean {

	@Autowired
	private CapitalConstructBean capitalConstructBean;

	@Autowired
	private AddressResolverBean addressResolverBean;

	@Autowired
	private ConstructTypeBean constructTypeBean;

	@Autowired
	private SpatialDataResolverBean spatialDataResolverBean;

	public CapitalConstruction resolve(ConstructDTO obj) {
		String cadastralNumber = obj.getCadastralNumber();
		CapitalConstruction capitalConstruction = capitalConstructBean.findByCadastralNumber(cadastralNumber);
		if (capitalConstruction == null) {
			capitalConstruction = new CapitalConstruction();
			updateConstruct(capitalConstruction, obj);
			capitalConstructBean.save(capitalConstruction);
		} else {
			updateConstruct(capitalConstruction, obj);
			capitalConstructBean.save(capitalConstruction);
		}
		return capitalConstruction;
	}

	private void updateConstruct(CapitalConstruction capitalConstruction, ConstructDTO constructDTO) {
		String cadastralNumber = constructDTO.getCadastralNumber();
		capitalConstruction.setCadastralNumber(cadastralNumber);
		// ? "CONSTRUCT"
		if (constructDTO instanceof BuildingDTO) {
			ConstructionType type = resolveType("BUILDING");
			capitalConstruction.setType(type);
			capitalConstruction.setName(type.getName() + " " + cadastralNumber);
		} else if (constructDTO instanceof IncompleteDTO) {
			ConstructionType type = resolveType("INCOMPLETE_CONSTRUCT");
			capitalConstruction.setType(type);
			capitalConstruction.setName(type.getName() + " " + cadastralNumber);
		} else {
			capitalConstruction.setName(cadastralNumber);
		}
		capitalConstruction.setAddress(addressResolverBean.resolveAddress(constructDTO.getAddress()));
		fillSpatialData(constructDTO, capitalConstruction);
	}

	private void fillSpatialData(ConstructDTO landDTO, CapitalConstruction land) {
		ConstructSourceDecorator sourceDecorator = new ConstructSourceDecorator();
		sourceDecorator.wrap(landDTO);

		ConstructionTargetDecorator targetDecorator = new ConstructionTargetDecorator();
		targetDecorator.wrap(land);

		spatialDataResolverBean.fillSpatialData(sourceDecorator, targetDecorator);
	}

	private ConstructionType resolveType(String code) {
		return constructTypeBean.findByCode(code);
	}

	public CapitalConstruction resolve(IncompleteDTO obj) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public void updateCoordinateSystem(Long id, CoordinateSystemDTO coordinateSystemDTO) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
