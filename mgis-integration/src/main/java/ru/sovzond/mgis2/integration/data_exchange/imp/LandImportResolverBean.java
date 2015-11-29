package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.address.AddressFilterBuilder;
import ru.sovzond.mgis2.geo.*;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.*;
import ru.sovzond.mgis2.kladr.KLADRLocality;
import ru.sovzond.mgis2.kladr.KLADRLocalityDao;
import ru.sovzond.mgis2.kladr.KLADRStreet;
import ru.sovzond.mgis2.kladr.KLADRStreetDao;
import ru.sovzond.mgis2.lands.*;
import ru.sovzond.mgis2.lands.characteristics.LandCharacteristics;
import ru.sovzond.mgis2.lands.rights.LandRights;
import ru.sovzond.mgis2.national_classifiers.LandCategoryBean;
import ru.sovzond.mgis2.national_classifiers.LandRightKindBean;
import ru.sovzond.mgis2.national_classifiers.OKATOBean;
import ru.sovzond.mgis2.national_classifiers.OKTMOBean;
import ru.sovzond.mgis2.registers.national_classifiers.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
@Service
public class LandImportResolverBean {

	public static final String CADASTRAL_BLOCK_PATTERN = "(\\d+):(\\d+):(\\d{2})(\\d{2})(\\d+).*";
	public static final String EPSG4326 = "EPSG:4326";
	public static final String EPSG4326_CONVERSION_RULES = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";

	@Autowired
	private LandBean landBean;

	@Autowired
	private AddressBean addressBean;

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
	private KLADRLocalityDao kladrLocalityDao;

	@Autowired
	private KLADRStreetDao kladrStreetDao;

	@Autowired
	private OKATOBean okatoBean;

	@Autowired
	private SpatialGroupBean spatialGroupBean;

	private Pattern cadastralNumberPattern = Pattern.compile(CADASTRAL_BLOCK_PATTERN);


	private Address resolveAddress(AddressDTO addressDTO) {
		AddressFilterBuilder filterBuilder = addressBean.createfilterBuilder();
		filterBuilder //
				.subjectCode(addressDTO.getRegion()) //
				.region(addressDTO.getDistrictName(), addressDTO.getDistrictType()) //
				.locality(addressDTO.getLocalityName(), addressDTO.getLocalityType()) //
				.street(addressDTO.getStreetName(), addressDTO.getStreetType()) //
				.home(addressDTO.getLevelValue()).housing(addressDTO.getLevelType()) //
		;
		List<Address> addresses = addressBean.find(filterBuilder.build());
		switch (addresses.size()) {
			case 0:
				return updateAddress(new Address(), addressDTO);
			case 1:
				return updateAddress(addresses.get(0), addressDTO);
			default:
				throw new UnsupportedOperationException("More than one address found for dto: " + addressDTO);
		}
	}

	private Address updateAddress(Address address, AddressDTO addressDTO) {
		address.setApartment(null);
		address.setBuilding(null);
		address.setHousing(null);
		address.setHousing(addressDTO.getLevelType());
		address.setHome(addressDTO.getLevelValue());

		KLADRLocality subject = kladrLocalityDao.findSubjectByCode(addressDTO.getRegion());
		KLADRLocality region = kladrLocalityDao.findRegion(subject.getCode(), addressDTO.getDistrictName(), addressDTO.getDistrictType()).get(0);
		KLADRLocality locality = kladrLocalityDao.findLocality(region.getCode(), addressDTO.getLocalityName(), addressDTO.getLocalityType()).get(0);
		KLADRStreet street = kladrStreetDao.findStreet(locality.getCode(), addressDTO.getStreetName(), addressDTO.getStreetType()).get(0);

		address.setSubject(subject);
		address.setRegion(region);
		address.setLocality(locality);
		address.setStreet(street);

		OKATO okato = okatoBean.findByCode("79");
		address.setOkato(okato);

		OKTMO oktmo = oktmoBean.list("79000000", null, null, 0, 0).getList().get(0);
		address.setOktmo(oktmo);

		address.setOther(addressDTO.getNote());
		// address.setPostalCode(addressDTO.getPostalCode());
		addressBean.save(address);
		return address;
	}

	private LandRightKind resolveLandRightKind(String name, String type) {
		return landRightKindBean.find(type);
	}

	public void updateCoordinateSystem(Long landId, CoordinateSystemDTO coordinateSystemDTO) {
		// TODO:
		Land land = landBean.load(landId);
		SpatialGroup spatialData = land.getSpatialData();
		if (spatialData != null) {
			CoordinateSystem coordinateSystem = resolveCoordinateSystem(coordinateSystemDTO.getName(), "-");
			spatialData.setCoordinateSystem(coordinateSystem);
			spatialGroupBean.save(spatialData);
			GeometryConverter converter = new GeometryConverter(coordinateSystem);
			land.setGeometry(converter.convert(spatialData.getSpatialElements()));
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
		land.setAddress(resolveAddress(landDTO.getAddress()));
		land.setAddressOfMunicipalEntity(resolveOKTMO(null));
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
			land.getRights().setTotalArea(landDTO.getArea().floatValue());
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
		return landCategoryBean.findByCode(category);
	}

	private OKTMO resolveOKTMO(String code) {
		return oktmoBean.findByCode(code);
	}

	private Land updateLand(LandDTO landDTO, Land land) {
		updateLand0(landDTO, land);
		return land;
	}
}