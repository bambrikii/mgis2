package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.geo.CoordinateSystem;
import ru.sovzond.mgis2.lands.Land;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
@Service
public class LandImportResolverBean {
	public Land resolveLand(String cadastralNumber) {
		throw new UnsupportedOperationException();
	}

	public Address resolveAddress(String okato,
								  String kladr,
								  String region,
								  String districtName, String districtType,
								  String localityName, String localityType,
								  String streetName, String streetType,
								  String level,
								  String note
	) {
		throw new UnsupportedOperationException();
	}

	public CoordinateSystem resolveCoordinateSystem(String name) {
		throw new UnsupportedOperationException();
	}

	public static class LandRightDTO {
		public LandRightKind kind;
		public String name;
	}

	public static class EntitySpatialDTO {
		public String entSys;
		public SpatialElementDTO[] spatialElements;
	}

	public static class SpatialElementDTO {
		public SpatialElemenUnitDTO[] spatialElementUnits;
	}

	public static class SpatialElemenUnitDTO {
		public OrdinateDTO[] ordinates;
	}

	public static class OrdinateDTO {
		public double x;
		public double y;
		public int ordNumber;
	}

	public LandRightKind resolveLandRightKind(String name, String type) {
		throw new UnsupportedOperationException();
	}

	public Land resolveLand(String cadastralNumber,
							String name,
							String state,
							String dateCreated,
							String area,
							String areaUnit,
							String locationInBounds,
							String locationPlaced,
							Address address,
							String category,
							LandRightDTO[] rights,
							Double cadastralCost,
							Integer cadastralCostUnit
	) {
		throw new UnsupportedOperationException();
	}
}
