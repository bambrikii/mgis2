package ru.sovzond.mgis2.integration.data_exchange.imp.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.geo.*;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.OrdinateDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.SpatialElementDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.SpatialElementUnitDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.SourceDecorator;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.TargetDecorator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */
@Service
public class SpatialDataResolverBean {

	public static final String EPSG4326 = "EPSG:4326";
	public static final String EPSG4326_CONVERSION_RULES = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";

	@Autowired
	private CoordinateSystemBean coordinateSystemBean;

	public void fillSpatialData(SourceDecorator landDTO, TargetDecorator land) {
		if (landDTO.getEntitySpatial() != null) {
			String entSys = landDTO.getEntitySpatial().getEntSys();
			//
			List<SpatialElementDTO> elements = landDTO.getEntitySpatial().getSpatialElements();
			if (elements != null) {
				SpatialGroup spatialGroup = new SpatialGroup();
				for (int i = 0; i < elements.size(); i++) {
					SpatialElementDTO element = elements.get(i);
					List<SpatialElementUnitDTO> units = element.getSpatialElementUnits();
					//
					SpatialElement spatialElement = new SpatialElement();
					spatialGroup.getSpatialElements().add(spatialElement);
					spatialElement.setPosition(BigDecimal.valueOf(i + 1));
					int accumulatedPosition = 0;
					for (SpatialElementUnitDTO unit : units) {
						int suNumb = unit.getSuNumb();
						String typeUnit = unit.getTypeUnit();
						List<OrdinateDTO> ordinates = unit.getOrdinates();
						for (OrdinateDTO ordinate : ordinates) {
							int ordNumber = ordinate.getOrdNumber();
							accumulatedPosition += ordNumber - 1;
							double x = ordinate.getX();
							double y = ordinate.getY();
							//
							Coordinate coordinate = new Coordinate();
							coordinate.setPosition(BigInteger.valueOf(suNumb == 1 ? 1 : (suNumb + accumulatedPosition)));
							coordinate.setX(BigDecimal.valueOf(x));
							coordinate.setY(BigDecimal.valueOf(y));
							spatialElement.getCoordinates().add(coordinate);
						}
					}
				}
				CoordinateSystem coordinateSystem = resolveCoordinateSystem(EPSG4326, EPSG4326_CONVERSION_RULES);
				spatialGroup.setCoordinateSystem(coordinateSystem);
				land.setSpatialData(spatialGroup);
			} else {
				land.setSpatialData(null);
			}
		} else {
			land.setSpatialData(null);
		}
	}

	public CoordinateSystem resolveCoordinateSystem(String code, String rules) {
		CoordinateSystem coordinateSystem = coordinateSystemBean.findByCode(code);
		if (coordinateSystem == null) {
			coordinateSystem = new CoordinateSystem();
			coordinateSystem.setCode(code);
			coordinateSystem.setConversionRules(rules);
			coordinateSystemBean.save(coordinateSystem);
		}
		return coordinateSystem;
	}
}
