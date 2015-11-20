package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.address.AddressBean;
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

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
@Service
public class LandImportResolverBean {

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

	private Address resolveAddress(AddressDTO addressDTO) {
		List<Address> addresses = addressBean.find(addressDTO.getOkato(), addressDTO.getKladr());
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
		land.setAllowedUsageByTerritorialZone(resolveTerritorialZone(landDTO.getLocationPlaced()));
		return land;
	}

	private TerritorialZone resolveTerritorialZone(String territorialZoneType) {
		List<TerritorialZone> list = territorialZoneBean.findByZoneTypeNameSubstring("%(" + territorialZoneType + ")%");
		TerritorialZone zone = null;
		switch (list.size()) {
			case 0:
				break;
			default:
				zone = list.get(0);
				break;
		}
		return zone;
	}

	private TerritorialZoneType resolveTerritorialZoneType(String territorialZoneType) {
		List<TerritorialZoneType> list = territorialZoneTypeBean.findByNameSubstring("%(" + territorialZoneType + ")%");
		TerritorialZoneType type = null;
		switch (list.size()) {
			case 0:
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
