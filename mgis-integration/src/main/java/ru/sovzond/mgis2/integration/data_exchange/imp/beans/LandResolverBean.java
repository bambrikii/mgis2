package ru.sovzond.mgis2.integration.data_exchange.imp.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.geo.*;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.*;
import ru.sovzond.mgis2.lands.*;
import ru.sovzond.mgis2.lands.characteristics.LandCharacteristics;
import ru.sovzond.mgis2.lands.rights.LandRights;
import ru.sovzond.mgis2.national_classifiers.LandCategoryBean;
import ru.sovzond.mgis2.national_classifiers.LandRightKindBean;
import ru.sovzond.mgis2.national_classifiers.OKTMOBean;
import ru.sovzond.mgis2.registers.national_classifiers.LandCategory;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
@Service
public class LandResolverBean {

	public static final String CADASTRAL_BLOCK_PATTERN = "(\\d+):(\\d+):(\\d{2})(\\d{2})(\\d+).*";
	public static final String EPSG4326 = "EPSG:4326";
	public static final String EPSG4326_CONVERSION_RULES = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";

	@Autowired
	private LandBean landBean;

	@Autowired
	private LandRightKindBean landRightKindBean;

	@Autowired
	private LandCategoryBean landCategoryBean;

	@Autowired
	private OKTMOBean oktmoBean;

	@Autowired
	private CoordinateSystemBean coordinateSystemBean;

	@Autowired
	private TerritorialZoneTypeBean territorialZoneTypeBean;

	@Autowired
	private TerritorialZoneBean territorialZoneBean;

	@Autowired
	private SpatialGroupBean spatialGroupBean;

	@Autowired
	private LandRightsBean landRightsBean;

	@Autowired
	private AddressResolverBean addressResolverBean;

	private Pattern cadastralNumberPattern = Pattern.compile(CADASTRAL_BLOCK_PATTERN);

	private LandRightKind resolveLandRightKind(String name, String type) {
		return landRightKindBean.find(type);
	}

	public void updateCoordinateSystem(Long landId, CoordinateSystemDTO coordinateSystemDTO) {
		// TODO:
		Land land = landBean.load(landId);
		SpatialGroup spatialData = land.getSpatialData();
		if (spatialData != null) {
			CoordinateSystem coordinateSystem = resolveCoordinateSystem(coordinateSystemDTO.getName(), null);
			spatialData.setCoordinateSystem(coordinateSystem);
			spatialGroupBean.save(spatialData);
			GeometryConverter converter = new GeometryConverter(coordinateSystem.getConversionRules());
			land.setGeometry(converter.convert(converter.createMultipolygon(spatialData.getSpatialElements())));
			landBean.save(land);
		}
	}

	public Land resolveLand(LandDTO landDTO) {
		List<Land> lands = landBean.find(landDTO.getCadastralNumber());
		Land land;
		switch (lands.size()) {
			case 0:
				land = createLand(landDTO);
				landBean.save(land);
				return land;
			case 1:
				land = updateLand(landDTO, lands.get(0));
				landBean.save(land);
				return land;
			default:
				throw new UnsupportedOperationException();
		}
	}

	private Land createLand(LandDTO landDTO) {
		Land land = new Land();
		land.setCharacteristics(new LandCharacteristics());
		land.setRights(new LandRights());
		landBean.save(land);

		updateLand0(landDTO, land);
		return land;
	}

	private void updateLand0(LandDTO landDTO, Land land) {
		land.setCadastralNumber(landDTO.getCadastralNumber());
		land.setStateRealEstateCadastreaStaging(landDTO.getDateCreated());
		land.setAddress(addressResolverBean.resolveAddress(landDTO.getAddress()));
//		land.setAddressOfMunicipalEntity(resolveOKTMO(null));
		land.setLandCategory(resolveLandCategory(landDTO.getCategory()));
		String locationPlaced = landDTO.getLocationPlaced();
		TerritorialZoneType zoneType;
		if (locationPlaced != null) {
			zoneType = resolveTerritorialZoneType(locationPlaced);
			land.setAllowedUsageByTerritorialZone(resolveTerritorialZone(landDTO.getCadastralNumber(), zoneType));
		} else {
			land.setAllowedUsageByTerritorialZone(null);
		}
		if (landDTO.getCadastralCostValue() != null) {
			land.getCharacteristics().setCadastralCost(landDTO.getCadastralCostValue().floatValue());
		}
		if (landDTO.getArea() != null) {
			LandRights rights = land.getRights();
			if (rights == null) {
				rights = new LandRights();
				landRightsBean.save(rights);
				land.setRights(rights);
			}
			rights.setTotalArea(landDTO.getArea().floatValue());
			if (landDTO.getRights() != null) {
				switch (landDTO.getRights().size()) {
					case 0:
						throw new IllegalArgumentException("No land right found while land.rights node container exists.");
					case 1:
						LandRightDTO landRightDTO = landDTO.getRights().get(0);
						rights.setRightKind(resolveLandRightKind(landRightDTO.getName(), landRightDTO.getType()));
						break;
					default:
						throw new IllegalArgumentException("More than one land right found in node land.rights node container exists.");
				}
			}
		}
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

	private CoordinateSystem resolveCoordinateSystem(String code, String rules) {
		CoordinateSystem coordinateSystem = coordinateSystemBean.findByCode(code);
		if (coordinateSystem == null) {
			coordinateSystem = new CoordinateSystem();
			coordinateSystem.setCode(code);
			coordinateSystem.setConversionRules(rules);
			coordinateSystemBean.save(coordinateSystem);
		}
		return coordinateSystem;
	}

	private TerritorialZone resolveTerritorialZone(String landCadastralNumber, TerritorialZoneType territorialZoneType) {
		Matcher matcher = cadastralNumberPattern.matcher(landCadastralNumber);
		if (matcher.matches()) {
			String cadastralNumber1 = matcher.group(1) + ":" + matcher.group(2) + ":" + matcher.group(3) + matcher.group(4) + matcher.group(5);
			List<TerritorialZone> list = territorialZoneBean.findByCadastralNumberAndZoneType(cadastralNumber1, territorialZoneType);
			switch (list.size()) {
				case 1:
					return list.get(0);
				case 0:
					String cadastralNumber2 = matcher.group(1) + ":" + matcher.group(2) + ":" + matcher.group(3) + matcher.group(4);
					list = territorialZoneBean.findByCadastralNumberAndZoneType(cadastralNumber2, territorialZoneType);
					switch (list.size()) {
						case 1:
							return list.get(0);
						case 0:
							TerritorialZone zone = new TerritorialZone();
							zone.setAccountNumber(cadastralNumber1);
							zone.setName(cadastralNumber1 + " (" + territorialZoneType.getName() + ")");
							zone.setZoneType(territorialZoneType);
							territorialZoneBean.save(zone);
							return zone;
						default:
							throw new IllegalArgumentException("More than one territorial zone found by cadastralNumber: " + cadastralNumber2 + " and territorialZoneType: " + territorialZoneType.getCode() + ".");
					}
				default:
					throw new IllegalArgumentException("More than one territorial zone found by cadastralNumber: " + cadastralNumber1 + " and territorialZoneType: " + territorialZoneType.getCode() + ".");
			}
		}
		throw new IllegalArgumentException("Cadastral number " + landCadastralNumber + " cannot be parsed.");
	}

	private TerritorialZoneType resolveTerritorialZoneType(String territorialZoneType) {
		TerritorialZoneType type = territorialZoneTypeBean.findByCode(territorialZoneType);
		if (type == null) {
			List<TerritorialZoneType> list = territorialZoneTypeBean.findByNameSubstring("%(" + territorialZoneType + ")%");
			switch (list.size()) {
				case 0:
					list = territorialZoneTypeBean.findByNameSubstring(territorialZoneType);
					switch (list.size()) {
						case 0:
							type = new TerritorialZoneType();
							type.setCode(territorialZoneType);
							type.setName(territorialZoneType);
							territorialZoneTypeBean.save(type);
							break;
						case 1:
							type = list.get(0);
							break;
						default:
							throw new IllegalArgumentException("More than one TerritorialZoneType found for name '" + territorialZoneType + "'");
					}
					break;
				case 1:
					type = list.get(0);
					break;
				default:
					throw new IllegalArgumentException("More than one TerritorialZoneType found for name '%(" + territorialZoneType + ")%'");
			}
		}
		return type;
	}

	private LandCategory resolveLandCategory(String category) {
		if (category != null) {
			LandCategory landCategory = landCategoryBean.findByCode(category);
			if (landCategory == null) {
				String category2 = category.replaceFirst("^0+", "");
				landCategory = landCategoryBean.findByCode(category2);
			}
			return landCategory;
		}
		return null;
	}

	private OKTMO resolveOKTMO(String code) {
		return oktmoBean.findByCode(code);
	}

	private Land updateLand(LandDTO landDTO, Land land) {
		updateLand0(landDTO, land);
		return land;
	}
}
