package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.address.AddressFilterBuilder;
import ru.sovzond.mgis2.geo.CoordinateSystem;
import ru.sovzond.mgis2.geo.CoordinateSystemBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.AddressDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;
import ru.sovzond.mgis2.lands.*;
import ru.sovzond.mgis2.national_classifiers.LandCategoryBean;
import ru.sovzond.mgis2.national_classifiers.LandRightKindBean;
import ru.sovzond.mgis2.national_classifiers.OKTMOBean;
import ru.sovzond.mgis2.registers.national_classifiers.LandCategory;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
@Service
public class LandImportResolverBean {

	public static final String CADASTRAL_BLOCK_PATTERN = "(\\d+):(\\d+):(\\d{2})(\\d{2})(\\d+).*";

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

	private Pattern cadastralNumberPattern = Pattern.compile(CADASTRAL_BLOCK_PATTERN);


	private Address resolveAddress(AddressDTO addressDTO) {
		// TODO:
		AddressFilterBuilder filterBuilder = addressBean.createfilterBuilder();
		filterBuilder
				.subject(addressDTO.getDistrictName(), addressDTO.getDistrictType())
		//.
		//.apartment(addressDTO.getLevelValue())
		;
		List<Address> addresses = addressBean.find(filterBuilder.build());
		switch (addresses.size()) {
			case 0:
				return null;
			case 1:
				return addresses.get(0);
			default:
				throw new UnsupportedOperationException("More than one address found for dto: " + addressDTO);
		}
	}

	private CoordinateSystem resolveCoordinateSystem(String name) {
		return coordinateSystemBean.findByCode(name);
	}

	private LandRightKind resolveLandRightKind(String name, String type) {
		return landRightKindBean.find(type);
	}

	public Land resolveLand(LandDTO landDTO) {
		List<Land> lands = landBean.find(landDTO.getCadastralNumber());
		switch (lands.size()) {
			case 0:
				return createLand(landDTO);
			case 1:
				return updateLand(landDTO, lands.get(0));
			default:
				throw new UnsupportedOperationException();
		}
	}

	private Land createLand(LandDTO landDTO) {
		Land land = new Land();
		copyPlainProperties(landDTO, land);
		land.setAddress(resolveAddress(landDTO.getAddress()));
		land.setAddressOfMunicipalEntity(resolveOKTMO(null));
		land.setLandCategory(resolveLandCategory(landDTO.getCategory()));
		TerritorialZoneType zoneType = resolveTerritorialZoneType(landDTO.getLocationPlaced());
		land.setAllowedUsageByTerritorialZone(resolveTerritorialZone(landDTO.getCadastralNumber(), zoneType));
		return land;
	}

	private TerritorialZone resolveTerritorialZone(String landCadastralNumber, TerritorialZoneType territorialZoneType) {
		TerritorialZone zone = null;
		Matcher matcher = cadastralNumberPattern.matcher(landCadastralNumber);
		if (matcher.matches()) {
			String cadastralNumber1 = matcher.group(1) + ":" + matcher.group(2) + ":" + matcher.group(3) + matcher.group(4) + matcher.group(5);
			List<TerritorialZone> list = territorialZoneBean.findByCadastralNumberAndZoneType(cadastralNumber1, territorialZoneType);
			if (list.size() >= 1) {
				zone = list.get(0);
			} else {
				String cadastralNumber2 = matcher.group(1) + ":" + matcher.group(2) + ":" + matcher.group(3) + matcher.group(4);
				list = territorialZoneBean.findByCadastralNumberAndZoneType(cadastralNumber2, territorialZoneType);
				if (list.size() >= 1) {
					zone = list.get(0);
				} else {
					zone = new TerritorialZone();
					zone.setAccountNumber(cadastralNumber1);
					zone.setName(cadastralNumber1 + " (" + territorialZoneType.getName() + ")");
					territorialZoneBean.save(zone);
				}
			}
		}
		return zone;
	}

	private TerritorialZoneType resolveTerritorialZoneType(String territorialZoneType) {
		List<TerritorialZoneType> list = territorialZoneTypeBean.findByNameSubstring("%(" + territorialZoneType + ")%");
		TerritorialZoneType type;
		switch (list.size()) {
			case 0:
				type = new TerritorialZoneType();
				type.setCode(territorialZoneType);
				type.setName(territorialZoneType);
				territorialZoneTypeBean.save(type);
				break;
			default:
				type = list.get(0);
				break;
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
		copyPlainProperties(landDTO, land);
		return land;
	}

	private void copyPlainProperties(LandDTO landDTO, Land land) {
		BeanUtils.copyProperties(landDTO, land, new String[]{"id", "landCategory", "landAreas", "allowedUsageByDictionary", "allowedUsageByTerritorialZone", "oktmo", "address", "rights", "characteristics", "includedObjects", "works", "control", "previousVersion", "geometry", "spatialData"});
	}
}
