package ru.sovzond.mgis2.integration.data_exchange.imp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sovzond.mgis2.integration.HibernateConfiguration;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.*;
import ru.sovzond.mgis2.lands.Land;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static ru.sovzond.mgis2.integration.data_exchange.imp.LandsImporter.YYYY_MM_DD;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
public class LandsImporterTest {

	@Autowired
	private LandsImporter landsImporter;

	@Autowired
	private LandImportResolverBean landImportResolverBean;

	@Test
	@Transactional
	public void testImp() throws IOException {
		try (InputStream inputStream = LandsImporterTest.class.getResourceAsStream("doc2394077.xml")) {
			landsImporter.imp(inputStream);
		}
	}

	@Test
	@Transactional
	public void testDTO() throws ParseException {
		// Land
		LandDTO landDTO = new LandDTO();
		landDTO.setCadastralNumber("72:17:0201005:2");
		landDTO.setName("01");
		landDTO.setState("01");
		landDTO.setDateCreated(new SimpleDateFormat(YYYY_MM_DD).parse("2001-04-20"));
		landDTO.setArea("243");
		landDTO.setAreaUnit("055");
		landDTO.setLocationInBounds("1");
		landDTO.setLocationPlaced("Ж2");

		// Address
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setOkato("71244812001");
		addressDTO.setKladr("72001000012000600");
		addressDTO.setRegion("72");
		addressDTO.setDistrictName("Тюменский");
		addressDTO.setDistrictType("р-н");
		addressDTO.setLocalityName("Боровский");
		addressDTO.setLocalityType("рп");
		addressDTO.setStreetName("Лермонтова");
		addressDTO.setStreetType("ул");
		addressDTO.setLevelType("д");
		addressDTO.setLevelValue("21а.");
		addressDTO.setNote("обл. Тюменская, р-н Тюменский, рп. Боровский, ул. Лермонтова, дом 21а.");
		landDTO.setCategory("003002000000");
		landDTO.setUtilizationByDoc("для индивидуального жилищного строительства и ведения подсобного хозяйства");

		// Rights
		LandRightDTO rightDTO = new LandRightDTO();
		rightDTO.setName("Собственность");
		rightDTO.setType("001001000000");
		landDTO.setRights(new LandRightDTO[]{rightDTO});

		landDTO.setCadastralCostValue(Double.valueOf("233783.01"));
		landDTO.setCadastralConstUnit(Integer.parseInt("383"));

		// Spatial Data
		EntitySpatialDTO entitySpatialDTO = new EntitySpatialDTO();
		entitySpatialDTO.setEntSys("364");
		SpatialElementDTO spatialElementDTO = new SpatialElementDTO();
		spatialElementDTO.setSpatialElementUnits(new SpatialElementUnitDTO[]{createSpatialElementUnit("Точка", "1", "325170.46", "1477361.18", "1")});

		landDTO.setEntitySpatial(entitySpatialDTO);


		landDTO.setAddress(addressDTO);
		Land land = landImportResolverBean.resolveLand(landDTO);
		Assert.assertNotNull(land);
	}

	private SpatialElementUnitDTO createSpatialElementUnit(String typeUnit, String suNumb, String x, String y, String ordNumb) {
		SpatialElementUnitDTO unitDTO = new SpatialElementUnitDTO();
		unitDTO.setTypeUnit(typeUnit);
		unitDTO.setSuNumb(Integer.parseInt(suNumb));
		OrdinateDTO ordinateDTO = new OrdinateDTO();
		ordinateDTO.setX(Double.parseDouble(x));
		ordinateDTO.setY(Double.parseDouble(y));
		ordinateDTO.setOrdNumber(Integer.parseInt(x));
		unitDTO.setOrdinates(new OrdinateDTO[]{ordinateDTO});
		return unitDTO;
	}
}
