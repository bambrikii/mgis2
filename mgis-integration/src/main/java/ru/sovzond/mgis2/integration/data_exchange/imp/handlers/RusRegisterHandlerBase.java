package ru.sovzond.mgis2.integration.data_exchange.imp.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.NamespaceSupport;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.BuildingBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.IncompleteBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.*;
import ru.sovzond.mgis2.integration.data_exchange.imp.impl.NodeNamesAdapter;
import ru.sovzond.mgis2.integration.data_exchange.imp.impl.PropertyExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.ILandResolver;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.*;

/**
 * Created by Alexander Arakelyan on 02.12.15.
 */
public abstract class RusRegisterHandlerBase extends DefaultHandler {

	AddressDTO addressDTO;
	EntitySpatialDTO entitySpatialDTO;
	LandDTO landDTO;
	LandRightDTO landRightDTO;
	OrdinateDTO ordinateDTO;
	SpatialElementDTO spatialElementDTO;
	SpatialElementUnitDTO spatialElementUnitDTO;
	CoordinateSystemDTO coordinateSystemDTO;
	List<LandRightDTO> landRightDTOs;
	List<SpatialElementDTO> spatialElementDTOs;
	List<SpatialElementUnitDTO> spatialElementUnitDTOs;
	List<OrdinateDTO> ordinateDTOs;
	/**
	 * ********************************************************
	 * тэги файла xml false - закрытый тэг , true - открытый
	 * ********************************************************
	 */

	boolean t_Region_Cadastr = false;
	boolean t_Cadastral_Blocks = false;
	boolean t_Cadastral_Block = false;
	boolean t_AreaM = false;//-----Area all
	//--------Parcels-----------
	boolean t_Parcels = false;
	boolean t_Parcel = false;
	boolean t_Area = false;//-----Area
	boolean t_AreaIn = false;
	boolean flagAreaIn = false;
	boolean t_Unit = false;
	//--------------------------
	boolean t_Location = false;//-----Location
	boolean t_inBounds = false;
	boolean t_Placed = false;
	//---------------------------
	boolean t_Address = false;
	boolean t_Code_OKATO = false;//-----Address (In Location)
	boolean t_Code_KLADR = false;
	boolean t_Region = false;
	boolean t_District = false;
	boolean t_Locality = false;
	boolean t_Street = false;
	boolean t_Level1 = false;
	boolean t_Note = false;
	//---------------------------
	boolean t_Category = false;
	boolean t_Utilization = false;
	//---------------------------
	boolean t_Rights = false;//-----Rights
	boolean t_Right = false;
	boolean t_Name_R = false;
	boolean t_Type_R = false;
	//---------------------------
	boolean t_CadastralCost = false;
	//-------OMS_Points-----------
	boolean t_OMSPoints = false;
	boolean t_OMSPoint = false;
	boolean t_PNmb = false;
	boolean t_PName = false;
	boolean t_PKlass = false;
	boolean t_OrdX = false;
	boolean t_OrdY = false;
	//-------SpatialData----------
	boolean t_SpatialData = false;
	boolean t_Entity_Spatial = false;
	boolean t_Spatial_Element = false;
	boolean t_Spelement_Unit = false;
	boolean t_Ordinate = false;
	//-------Bounds---------------
	boolean t_Bounds = false;
	boolean t_Bound = false;
	boolean t_AccountNumber = false;
	boolean t_Boundaries = false;
	boolean t_Boundary = false;
	boolean t_Documents = false;
	boolean t_Document = false;
	boolean t_Name_B = false;
	boolean t_Number = false;
	boolean t_IssueOrgan = false;
	boolean t_MunicipalBoundary = false;
	boolean t_Name_MB = false;
	//-------Coord_System---------
	boolean t_Coord_System = false;
	//-------Certification_Doc-----
	boolean t_Certification_Doc = false;
	boolean t_Organization = false;
	boolean t_Date = false;
	boolean t_Number_Cert = false;
	boolean t_Appointment = false;
	boolean t_FIO = false;
	SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
	private NamespaceSupport namespaceSupport;
	private String[] qNames = new String[3];

	private PropertyExtractor<LandDTO, String> categoryPropertyExtractor;
	private NodeNamesAdapter extractor;

	private final BuildingBuilder buildingBuilder;
	private final IncompleteBuilder incompleteBuilder;
	private boolean t_objectRealty;
	private ILandResolver<LandDTO> landResolver;
	private ILandResolver<BuildingDTO> buildingResolver;
	private ILandResolver<IncompleteDTO> incompleteConstructResolver;

	public RusRegisterHandlerBase(
			ILandResolver<LandDTO> landResolver,
			ILandResolver<BuildingDTO> buildingResolver,
			ILandResolver<IncompleteDTO> incompleteConstructResolver,
			Class<?> propertyClass,
			PropertyExtractor<LandDTO, String> categoryPropertyExtractor
	) {
		this.landResolver = landResolver;
		this.buildingResolver = buildingResolver;
		this.incompleteConstructResolver = incompleteConstructResolver;
		this.categoryPropertyExtractor = categoryPropertyExtractor;
		extractor = new NodeNamesAdapter(propertyClass);

		buildingBuilder = new BuildingBuilder(
				qName -> byNode(qName, BUILDING),
				qName -> byNode(qName, ASSIGNATION_BUILDING),
				qName -> byNode(qName, OBJECT_TYPE),
				qName -> byNode(qName, AREA),
				qName -> byNode(qName, ADDRESS),
				qName -> byNode(qName, CODE_OKATO),
				qName -> byNode(qName, CODE_KLADR),
				qName -> byNode(qName, REGION),
				qName -> byNode(qName, DISTRICT),
				qName -> byNode(qName, LOCALITY),
				qName -> byNode(qName, STREET),
				qName -> byNode(qName, LEVEL_1),
				qName -> byNode(qName, NOTE),
				qName -> byNode(qName, CADASTRAL_COST),
				qName -> byNode(qName, ENTITY_SPATIAL),
				qName -> byNode(qName, SPATIAL_ELEMENT),
				qName -> byNode(qName, SPELEMENT_UNIT),
				qName -> byNode(qName, ORDINATE),
				nodeBuilder -> {
					BuildingDTO result = nodeBuilder.build();
					if (result != null) {
						this.buildingResolver.resolve(result);
					}
					nodeBuilder.reset();
				}
		);
		incompleteBuilder = new IncompleteBuilder(
				qName -> byNode(qName, INCOMPLETE),
				qName -> byNode(qName, ASSIGNATION_BUILDING),
				qName -> byNode(qName, OBJECT_TYPE),
				qName -> byNode(qName, AREA),
				qName -> byNode(qName, ADDRESS),
				qName -> byNode(qName, CODE_OKATO),
				qName -> byNode(qName, CODE_KLADR),
				qName -> byNode(qName, REGION),
				qName -> byNode(qName, DISTRICT),
				qName -> byNode(qName, LOCALITY),
				qName -> byNode(qName, STREET),
				qName -> byNode(qName, LEVEL_1),
				qName -> byNode(qName, NOTE),
				qName -> byNode(qName, CADASTRAL_COST),
				qName -> byNode(qName, ENTITY_SPATIAL),
				qName -> byNode(qName, SPATIAL_ELEMENT),
				qName -> byNode(qName, SPELEMENT_UNIT),
				qName -> byNode(qName, ORDINATE),
				nodeBuilder -> {
					IncompleteDTO result = nodeBuilder.build();
					if (result != null) {
						this.incompleteConstructResolver.resolve(result);
					}
					nodeBuilder.reset();
				}
		);
	}

	private boolean byNode(String qName, String regionCadastr) {
		return extractor.byNode(qName, regionCadastr);
	}

	private String byNodeAttr(Attributes attributes, String name) {
		return extractor.byNodeAttr(attributes, name);
	}

	private boolean byNodeEndsWith(String qName, String area) {
		return extractor.byNodeEndsWith(qName, area);
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		namespaceSupport = new NamespaceSupport();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		namespaceSupport.declarePrefix(prefix, uri);
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {

	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		namespaceSupport.processName(qName, qNames, false);
		String qName2 = qNames[1];

		//-------------Cadastral_Blocks---------
		if (byNode(qName2, REGION_CADASTR)) {
			t_Region_Cadastr = true;
		}
		if (byNode(qName2, CADASTRAL_BLOCKS)) {
			t_Cadastral_Blocks = true;
		}
		if (byNode(qName2, CADASTRAL_BLOCK)) {
			t_Cadastral_Block = true;
		}

		//-------------Parcels------------------
		if (byNode(qName2, PARCELS) && t_Region_Cadastr && t_Cadastral_Blocks && t_Cadastral_Block) {
			t_Parcels = true;
		}

		if (byNode(qName2, PARCEL) && t_Parcels) {
			t_Parcel = true;
			landDTO = new LandDTO();
			landDTO.setCadastralNumber(byNodeAttr(attributes, CADASTRAL_NUMBER_ATTR));
			landDTO.setName(byNodeAttr(attributes, NAME_ATTR));
			landDTO.setState(byNodeAttr(attributes, STATE_ATTR));
			landDTO.setDateCreated(toDate(byNodeAttr(attributes, DATE_CR_ATTR), dateFormat));
		}


		//-------------Area---------------------
		if (byNode(qName2, AREA) && t_Area) {
			t_AreaIn = true;
			flagAreaIn = true;
		}

		if (byNode(qName2, AREA) && t_Parcel && !t_Area) {
			t_Area = true;
			flagAreaIn = false;
		}


		if (byNode(qName2, UNIT) && t_Area) {
			t_Unit = true;
		}

		//--------Location---------------------
		if (byNode(qName2, LOCATION)) {
			t_Location = true;
		}

		if (byNode(qName2, IN_BOUNDS) && t_Location) {
			t_inBounds = true;
		}

		if (byNode(qName2, PLACED) && t_Location) {
			t_Placed = true;
		}

		//-------Address------------------------
		if (byNode(qName2, ADDRESS) && t_Location) {
			t_Address = true;
			addressDTO = new AddressDTO();
		}

		if (byNode(qName2, CODE_OKATO) && t_Address) {
			t_Code_OKATO = true;
		}

		if (byNode(qName2, CODE_KLADR) && t_Address) {
			t_Code_KLADR = true;
		}

		if (byNode(qName2, REGION) && t_Address) {
			t_Region = true;
		}

		if (byNode(qName2, DISTRICT) && t_Address) {
			t_District = true;
			addressDTO.setDistrictName(byNodeAttr(attributes, NAME_ALL_ATTR));
			addressDTO.setDistrictType(byNodeAttr(attributes, TYPE_ALL_ATTR));
			t_District = false;
		}

		if (byNode(qName2, LOCALITY) && t_Address) {
			t_Locality = true;
			addressDTO.setLocalityName(byNodeAttr(attributes, NAME_ALL_ATTR));
			addressDTO.setLocalityType(byNodeAttr(attributes, TYPE_ALL_ATTR));
			t_Locality = false;
		}

		if (byNode(qName2, STREET) && t_Address) {
			t_Street = true;
			addressDTO.setStreetName(byNodeAttr(attributes, NAME_ALL_ATTR));
			addressDTO.setStreetType(byNodeAttr(attributes, TYPE_ALL_ATTR));
			t_Street = false;
		}

		if (byNode(qName2, LEVEL_1) && t_Address) {
			t_Level1 = true;
			addressDTO.setLevelType(byNodeAttr(attributes, TYPE_ALL_ATTR));
			addressDTO.setLevelValue(byNodeAttr(attributes, VALUE_ATTR));
			t_Level1 = false;
		}

		if (byNode(qName2, NOTE) && t_Address) {
			t_Note = true;
		}

		//-------Category-----------------------
		if (byNode(qName2, CATEGORY) && t_Parcel) {
			t_Category = true;
			categoryPropertyExtractor.extractFromAttribute(landDTO, () -> byNodeAttr(attributes, CATEGORY_ATTR));
		}

		//-------Utiliz--------------------------
		if (byNode(qName2, UTILIZATION) && t_Parcel) {
			t_Utilization = true;
			landDTO.setUtilizationByDoc(byNodeAttr(attributes, UTILIZ_ATTR));
			t_Utilization = false;
		}

		//---------Rights------------------------
		if (byNode(qName2, RIGHTS)) {
			t_Rights = true;
			landRightDTOs = new ArrayList<>();
		}

		if (byNode(qName2, RIGHT) && t_Rights) {
			t_Right = true;
			landRightDTO = new LandRightDTO();
		}
		if (byNode(qName2, NAME) && t_Right) {
			t_Name_R = true;
		}

		if (byNode(qName2, TYPE) && t_Right) {
			t_Type_R = true;
		}

		//-----------Cadastral cost----------------
		if (byNode(qName2, CADASTRAL_COST) && t_Parcel) {
			t_CadastralCost = true;
			landDTO.setCadastralCostValue(Double.valueOf(byNodeAttr(attributes, CADASTRAL_COST_VALUE_ATTR)));
			landDTO.setCadastralCostUnit(Integer.valueOf(byNodeAttr(attributes, CADASTRAL_COST_UNIT_ATTR)));
			t_CadastralCost = false;
		}

		//-----SpatialData-------------------------
		if (byNode(qName2, ENTITY_SPATIAL) && t_Parcel) {
			t_Entity_Spatial = true;
			entitySpatialDTO = new EntitySpatialDTO();
			entitySpatialDTO.setEntSys(byNodeAttr(attributes, ENT_SYS_ATTR));
			spatialElementDTOs = new ArrayList<>();

		}

		//-----Spatial Element---------------------
		if (byNode(qName2, SPATIAL_ELEMENT) && t_Entity_Spatial) {
			t_Spatial_Element = true;
			spatialElementDTO = new SpatialElementDTO();
			spatialElementUnitDTOs = new ArrayList<>();
		}

		if (byNode(qName2, SPELEMENT_UNIT) && t_Spatial_Element) {
			t_Spelement_Unit = true;
			spatialElementUnitDTO = new SpatialElementUnitDTO();
			ordinateDTOs = new ArrayList<>();
			spatialElementUnitDTO.setTypeUnit(byNodeAttr(attributes, TYPE_UNIT_ATTR));
			spatialElementUnitDTO.setSuNumb(Integer.parseInt(byNodeAttr(attributes, SU_NMB_ATTR)));
		}

		if (byNode(qName2, ORDINATE) && t_Spelement_Unit) {
			t_Ordinate = true;
			ordinateDTO = new OrdinateDTO();
			ordinateDTO.setX(Double.valueOf(byNodeAttr(attributes, X_ATTR)));
			ordinateDTO.setY(Double.valueOf(byNodeAttr(attributes, Y_ATTR)));
			ordinateDTO.setOrdNumber(Integer.valueOf(byNodeAttr(attributes, ORD_ATTR)));
			ordinateDTOs.add(ordinateDTO);
			t_Ordinate = false;
		}

		if (byNode(qName2, COORD_SYSTEM) && t_Cadastral_Block) {
			t_Coord_System = true;
			buildCoordSystem(attributes);
			landResolver.updateCoordinateSystem(coordinateSystemDTO);
			buildingResolver.updateCoordinateSystem(coordinateSystemDTO);
			incompleteConstructResolver.updateCoordinateSystem(coordinateSystemDTO);
		}

		// Capital Constructs
		if (byNode(qName2, OBJECT_REALTY)) {
			t_objectRealty = true;
		}

		buildingBuilder.start(qName2, attrName -> byNodeAttr(attributes, attrName));
		incompleteBuilder.start(qName2, attrName -> byNodeAttr(attributes, attrName));
	}

	private void buildCoordSystem(Attributes attributes) {
		coordinateSystemDTO = new CoordinateSystemDTO();
		coordinateSystemDTO.setId(byNodeAttr(attributes, CS_ID_ATTR));
		coordinateSystemDTO.setName(byNodeAttr(attributes, NAME_COORD_SYS_ATTR));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		namespaceSupport.processName(qName, qNames, false);
		String qName2 = qNames[1];

		//-----Area--------------
		if (t_Area && byNodeEndsWith(qName, AREA) && !t_AreaIn && !flagAreaIn) {
			t_Area = false;
		}

		if (t_AreaIn && flagAreaIn && byNodeEndsWith(qName, AREA)) {
			t_AreaIn = false;
			flagAreaIn = false;
		}
		if (t_Area && t_Unit && byNodeEndsWith(qName, UNIT)) {
			t_Unit = false;
		}
		//----Location-----------
		if (t_Location && t_inBounds && byNodeEndsWith(qName, IN_BOUNDS)) {
			t_inBounds = false;
		}
		if (t_Location && t_Placed && byNodeEndsWith(qName, PLACED)) {
			t_Placed = false;
		}
		if (t_Parcel && byNodeEndsWith(qName, CATEGORY)) {
			t_Category = false;
		}
		if (t_Address && t_Code_OKATO && byNodeEndsWith(qName, CODE_OKATO)) {
			t_Code_OKATO = false;
		}
		if (t_Address && t_Code_KLADR && byNodeEndsWith(qName, CODE_KLADR)) {
			t_Code_KLADR = false;
		}
		if (t_Address && t_Region && byNodeEndsWith(qName, REGION)) {
			t_Region = false;
		}
		if (t_Address && t_Note && byNodeEndsWith(qName, NOTE)) {
			t_Note = false;
		}

		if (t_Address && byNodeEndsWith(qName, ADDRESS)) {
			landDTO.setAddress(addressDTO);
			t_Address = false;
		}

		if (t_Location && byNodeEndsWith(qName, LOCATION)) {
			t_Location = false;
		}
		//-------Rights------------
		if (t_Right && t_Name_R && byNodeEndsWith(qName, NAME)) {
			t_Name_R = false;
		}
		if (t_Right && t_Type_R && byNodeEndsWith(qName, TYPE)) {
			t_Type_R = false;
		}

		if (t_Rights && t_Right && byNodeEndsWith(qName, RIGHT)) {
			landRightDTOs.add(landRightDTO);
			t_Right = false;
		}

		if (t_Rights && byNodeEndsWith(qName, RIGHTS)) {
			landDTO.setRights(landRightDTOs);
			t_Rights = false;
		}
		//------Spetial Data-------
		if (t_Spelement_Unit && byNodeEndsWith(qName, SPELEMENT_UNIT)) {
			spatialElementUnitDTO.setOrdinates(ordinateDTOs);
			spatialElementUnitDTOs.add(spatialElementUnitDTO);
			t_Spelement_Unit = false;
		}

		if (t_Spatial_Element && byNodeEndsWith(qName, SPATIAL_ELEMENT)) {
			spatialElementDTO.setSpatialElementUnits(spatialElementUnitDTOs);
			spatialElementDTOs.add(spatialElementDTO);
			t_Spatial_Element = false;
		}

		if (t_Entity_Spatial && byNodeEndsWith(qName, ENTITY_SPATIAL)) {
			entitySpatialDTO.setSpatialElements(spatialElementDTOs);
			landDTO.setEntitySpatial(entitySpatialDTO);
			t_Entity_Spatial = false;
		}

		if (t_Parcel && byNodeEndsWith(qName, PARCEL)) {
			landResolver.resolve(landDTO);
			t_Parcel = false;
		}

		if (t_Parcels && byNodeEndsWith(qName, PARCELS)) {
			t_Parcels = false;
		}

		if (t_Cadastral_Block && byNodeEndsWith(qName, CADASTRAL_BLOCK)) {
			t_Cadastral_Block = false;
		}

		if (t_Cadastral_Blocks && byNodeEndsWith(qName, CADASTRAL_BLOCKS)) {
			t_Cadastral_Blocks = false;
		}
		if (t_Region_Cadastr && byNodeEndsWith(qName, REGION_CADASTR)) {
			t_Region_Cadastr = false;
		}

		if (t_Cadastral_Block && byNode(qName2, COORD_SYSTEM)) {
			t_Coord_System = false;
		}

		// Capital Constructs
		if (t_objectRealty && byNodeEndsWith(qName, OBJECT_REALTY)) {
			t_objectRealty = false;
		}

		buildingBuilder.end(qName2);
		incompleteBuilder.end(qName2);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		//----------AREA------------
		if (t_Area && t_AreaIn && flagAreaIn) {
			landDTO.setArea(Double.parseDouble(String.valueOf(ch, start, length)));
		}

		if (t_Area && t_Unit) {
			landDTO.setAreaUnit(String.valueOf(ch, start, length));
		}
		//----------LOCATION---------
		if (t_inBounds) {
			landDTO.setLocationInBounds(String.valueOf(ch, start, length));
		}

		if (t_Placed) {
			landDTO.setLocationPlaced(String.valueOf(ch, start, length));
		}

		//----------CATEGORY----------
		if (t_Category) {
			categoryPropertyExtractor.extractFromChars(landDTO, () -> String.valueOf(ch, start, length));
		}

		//----------ADDRESS-----------
		if (t_Code_OKATO) {
			addressDTO.setOkato(String.valueOf(ch, start, length));
		}
		if (t_Code_KLADR) {
			addressDTO.setKladr(String.valueOf(ch, start, length));
		}
		if (t_Region) {
			addressDTO.setRegion(String.valueOf(ch, start, length));
		}
		if (t_Note) {
			addressDTO.setNote(String.valueOf(ch, start, length));
		}

		//----------RIGHTS---------
		if (t_Name_R) {
			landRightDTO.setName(String.valueOf(ch, start, length));
		}

		if (t_Type_R) {
			landRightDTO.setType(String.valueOf(ch, start, length));
		}

		buildingBuilder.content(String.valueOf(ch, start, length));
		incompleteBuilder.content(String.valueOf(ch, start, length));
	}

	@Override
	public void error(SAXParseException e) {
		e.printStackTrace();
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		super.fatalError(e);
		e.printStackTrace();
	}

	@Override
	public void warning(SAXParseException e) {
		e.printStackTrace();
	}

	private Date toDate(String dateS, SimpleDateFormat dateFormat) {
		try {
			return new Date(dateFormat.parse(dateS).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
