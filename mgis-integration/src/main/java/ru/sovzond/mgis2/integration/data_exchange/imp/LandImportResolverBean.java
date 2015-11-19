package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.address.Address;
import ru.sovzond.mgis2.address.AddressBean;
import ru.sovzond.mgis2.geo.CoordinateSystem;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.AddressDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;
import ru.sovzond.mgis2.lands.Land;
import ru.sovzond.mgis2.lands.LandBean;
import ru.sovzond.mgis2.national_classifiers.LandRightKindBean;
import ru.sovzond.mgis2.registers.national_classifiers.LandRightKind;

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

	private Address resolveAddress(AddressDTO addressDTO) {
//		addressBean.
		throw new UnsupportedOperationException();
	}

	private CoordinateSystem resolveCoordinateSystem(String name) {
		throw new UnsupportedOperationException();
	}

	private LandRightKind resolveLandRightKind(String name, String type) {
		throw new UnsupportedOperationException();
	}

	private Land resolveLand(String cadastralNumber) {
		throw new UnsupportedOperationException();
	}

	public Land resolveLand(LandDTO landDTO) {
//		Land land = landBean.findByCadastralNumber(landDTO.cadastralNumber);
		throw new UnsupportedOperationException();
	}
}
