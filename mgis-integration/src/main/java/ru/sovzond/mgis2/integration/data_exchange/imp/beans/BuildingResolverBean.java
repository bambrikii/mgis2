package ru.sovzond.mgis2.integration.data_exchange.imp.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.capital_construct.CapitalConstructBean;
import ru.sovzond.mgis2.capital_construct.ConstructTypeBean;
import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.capital_constructs.ConstructionType;
import ru.sovzond.mgis2.capital_constructs.characteristics.ConstructionCharacteristics;
import ru.sovzond.mgis2.geo.CoordinateSystem;
import ru.sovzond.mgis2.geo.SpatialDataBean;
import ru.sovzond.mgis2.geo.SpatialGroup;
import ru.sovzond.mgis2.geo.SpatialGroupBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.BuildingDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.ConstructDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.IncompleteDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.ConstructSourceDecorator;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.ConstructionTargetDecorator;
import ru.sovzond.mgis2.lands.includes.LandIncludedObjects;
import ru.sovzond.mgis2.rights.PropertyRights;

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
	private SpatialGroupBean spatialGroupBean;

	@Autowired
	private SpatialDataBean spatialDataBean;

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
		ConstructionType type;
		if (constructDTO instanceof BuildingDTO) {
			type = resolveType("BUILDING");
		} else if (constructDTO instanceof IncompleteDTO) {
			type = resolveType("INCOMPLETE_CONSTRUCT");
		} else {
			type = resolveType("CONSTRUCT");
		}
		capitalConstruction.setType(type);
		capitalConstruction.setName(type.getName() + " " + cadastralNumber);
		capitalConstruction.setAddress(addressResolverBean.resolveAddress(constructDTO.getAddress()));

		PropertyRights rights = capitalConstruction.getRights();
		if (rights == null) {
			rights = new PropertyRights();
			capitalConstruction.setRights(rights);
		}

		ConstructionCharacteristics constructionCharacteristics = capitalConstruction.getCharacteristics();
		if (constructionCharacteristics == null) {
			constructionCharacteristics = new ConstructionCharacteristics();
			capitalConstruction.setCharacteristics(constructionCharacteristics);
		}

		LandIncludedObjects landIncludedObjects = capitalConstruction.getLandIncludedObjects();
		if (landIncludedObjects == null) {
			landIncludedObjects = new LandIncludedObjects();
			capitalConstruction.setLandIncludedObjects(landIncludedObjects);
		}

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

	public void updateCoordinateSystem(Long id, CoordinateSystemDTO coordinateSystemDTO) {
		CapitalConstruction construct = capitalConstructBean.load(id);
		SpatialGroup spatialData = construct.getSpatialData();
		if (spatialData != null) {
			CoordinateSystem coordinateSystem = spatialDataResolverBean.resolveCoordinateSystem(coordinateSystemDTO.getName(), null);
			spatialData.setCoordinateSystem(coordinateSystem);
			spatialGroupBean.save(spatialData);
			construct.setGeometry(spatialDataBean.buildGeometry(spatialData));
			capitalConstructBean.save(construct);
		}
	}
}
