package ru.sovzond.mgis2.integration.data_exchange.imp.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ru.sovzond.mgis2.integration.data_exchange.imp.ILandResolver;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 * <p/>
 * Region
 * - Package
 * -  Cadastral_Blocks
 * -   Cadastral_Block
 * -    Area
 * -    Parcels
 * -     Parcel[CadastralNumber,Name,State,DateCreated]
 * -      Area
 * -       Area
 * -       Unit
 * -      Location
 * -       inBounds
 * -       Placed
 * -       Address
 * -        Code_OKATO
 * -        Code_KLADR
 * -        Region
 * -        District[Name,Type]
 * -        Locality[Name,Type]
 * -        Street[Name,Type]
 * -        Level1
 * -        Note
 * -    OMSPoints
 * -     OMSPoint
 * -      PNmb
 * -      PName
 * -      PKlass
 * -      OrdX
 * -      OrdY
 * -    SpatialData
 * -     Entity_Spatial[Ent_Sys]
 * -      Spatial_Element
 * -       Spelement_Unit[Type_Unit,Su_Nmb]
 * -        Ordinate[X,Y,Ord_Nmb]
 * -     Documents
 * -      Document
 * -       Code_Document
 * -       Name
 * -       Number
 * -       IssueOrgan
 * -    Bounds
 * -     Bound
 * -      AccountNumber
 * -      Boundaries
 * -       Boundary
 * -        Entity_Spatial[Ent_Sys]
 * -         Spatial_Element
 * -          Spelement_Unit[Type_Unit,Su_Nmb]
 * -           Ordinate[X,Y,Ord_Nmb,Geopoint_Opred,Delta_Geopoint]
 * -      Documents
 * -       Document
 * -        Code_Document
 * -        Name
 * -        Number
 * -        Date
 * -        IssueOrgan
 * -      InhabitedLocalityBoundary
 * -       Name
 * -    Coord_System[Name,Cs_Id]
 * -  Certification_Doc
 * -   Organization
 * -   Date
 * -   Number
 * -   Appointment
 * -   FIO
 * -
 */
public class Region_CadastrHandler extends DefaultHandler {

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

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
	 * Константы названия атрибутов
	 * ********************************************************
	 */

	//-------All attributes name--------
	final static String NAME_ALL = "Name";
	final static String TYPE_ALL = "Type";
	final static String VALUE = "Type";
	//-------Parcel attributes name-----
	final static String CADASTRAL_NUMBER = "CadastralNumber";
	final static String NAME = "Name";
	final static String STATE = "State";
	final static String DATE_CR = "DateCreated";
	final static String CATEGORY = "Category";
	final static String UTILIZ = "ByDoc";
	final static String CADASTRAL_COST_VALUE = "Value";
	final static String CADASTRAL_COST_UNIT = "Unit";
	//-------UNIT-----------------------
	final static String ENT_SYS = "Ent_Sys";
	final static String TOTAL = "Total";
	final static String UNIT = "Unit";
	final static String TYPE_UNIT = "Type_Unit";
	final static String SU_NMB = "Su_Nmb";
	final static String X = "X";
	final static String Y = "Y";
	final static String ORD = "Ord_Nmb";
	//-------Bounds----------------------
	final static String ENT_SYS_BOUNDS = "Ent_Sys";
	//-------Coord-Sys----------------------
	final static String NAME_COORD_SYS = "Name";
	final static String CS_ID = "Cs_Id";

	/**
	 * ********************************************************
	 * тэги файла xml false - закрытый тэг , true - открытый
	 * ********************************************************
	 */

	boolean t_Cadastral_Blocks = false;
	boolean t_Cadastral_Block = false;
	boolean t_AreaM = false;//-----Area all
	//--------Parcels-----------
	boolean t_Parcels = false;
	boolean t_Parcel = false;
	boolean t_Area = false;//-----Area
	boolean t_AreaIn = false;
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

	private ILandResolver landResolver;

	public Region_CadastrHandler(ILandResolver landResolver) {
		this.landResolver = landResolver;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		//-------------Cadastral_Blocks---------
		if (qName.equalsIgnoreCase("Cadastral_Blocks")) {
			t_Cadastral_Blocks = true;
		}
		if (qName.equalsIgnoreCase("Cadastral_Block")) {
			t_Cadastral_Block = true;
		}

		//-------------Parcels------------------
		if (qName.equalsIgnoreCase("Parcels")) {
			t_Parcels = true;
		}

		if (qName.equalsIgnoreCase("Parcel") && t_Parcels) {
			t_Parcel = true;
			landDTO = new LandDTO();
			landDTO.setCadastralNumber(attributes.getValue(CADASTRAL_NUMBER));
			landDTO.setName(attributes.getValue(NAME));
			landDTO.setState(attributes.getValue(STATE));
			landDTO.setDateCreated(toDate(attributes.getValue(DATE_CR), dateFormat));
		}


		//-------------Area---------------------
		if (qName.equalsIgnoreCase("Area") && t_Area) {
			t_AreaIn = true;
		}
		if (qName.equalsIgnoreCase("Area") && t_Parcel && !t_Area) {
			t_Area = true;
		}



		if (qName.equalsIgnoreCase("Unit") && t_Area) {
			t_Unit = true;
		}

		//--------Location---------------------
		if (qName.equalsIgnoreCase("Location")) {
			t_Location = true;
		}

		if (qName.equalsIgnoreCase("inBounds") && t_Location) {
			t_inBounds = true;
		}

		if (qName.equalsIgnoreCase("Placed") && t_Location) {
			t_Placed = true;
		}

		//-------Address------------------------
		if (qName.equalsIgnoreCase("Address") && t_Location) {
			t_Address = true;
			addressDTO = new AddressDTO();
		}

		if (qName.equalsIgnoreCase("Code_OKATO") && t_Address) {
			t_Code_OKATO = true;
		}

		if (qName.equalsIgnoreCase("Code_KLADR") && t_Address) {
			t_Code_KLADR = true;
		}

		if (qName.equalsIgnoreCase("Region") && t_Address) {
			t_Region = true;
		}

		if (qName.equalsIgnoreCase("District") && t_Address) {
			t_District = true;
			addressDTO.setDistrictName(attributes.getValue(NAME_ALL));
			addressDTO.setDistrictType(attributes.getValue(TYPE_ALL));
			t_District = false;
		}

		if (qName.equalsIgnoreCase("Locality") && t_Address) {
			t_Locality = true;
			addressDTO.setLocalityName(attributes.getValue(NAME_ALL));
			addressDTO.setLocalityType(attributes.getValue(TYPE_ALL));
			t_Locality = false;
		}

		if (qName.equalsIgnoreCase("Street") && t_Address) {
			t_Street = true;
			addressDTO.setStreetName(attributes.getValue(NAME_ALL));
			addressDTO.setStreetType(attributes.getValue(TYPE_ALL));
			t_Street = false;
		}

		if (qName.equalsIgnoreCase("Level1") && t_Address) {
			t_Level1 = true;
			addressDTO.setLevelType(attributes.getValue(TYPE_ALL));
			addressDTO.setLevelValue(attributes.getValue(VALUE));
			t_Level1 = false;
		}

		if (qName.equalsIgnoreCase("Note") && t_Address) {
			t_Note = true;
		}

		//-------Category-----------------------
		if (qName.equalsIgnoreCase("Category") && t_Parcel) {
			t_Category = true;
			landDTO.setCategory(attributes.getValue(CATEGORY));
			t_Category = false;
		}

		//-------Utiliz--------------------------
		if (qName.equalsIgnoreCase("Utilization") && t_Parcel) {
			t_Utilization = true;
			landDTO.setUtilizationByDoc(attributes.getValue(UTILIZ));
			t_Utilization = false;
		}

		//---------Rights------------------------
		if (qName.equalsIgnoreCase("Rights")) {
			t_Rights = true;
			landRightDTOs = new ArrayList<LandRightDTO>();
		}

		if (qName.equalsIgnoreCase("Right") && t_Rights) {
			t_Right = true;
			landRightDTO = new LandRightDTO();
		}
		if (qName.equalsIgnoreCase("Name") && t_Right) {
			t_Name_R = true;
		}

		if (qName.equalsIgnoreCase("Type") && t_Right) {
			t_Type_R = true;
		}

		//-----------Cadastral cost----------------
		if (qName.equalsIgnoreCase("CadastralCost")) {
			t_CadastralCost = true;
			landDTO.setCadastralCostValue(Double.valueOf(attributes.getValue(CADASTRAL_COST_VALUE)));
			landDTO.setCadastralCostUnit(Integer.valueOf(attributes.getValue(CADASTRAL_COST_UNIT)));
			t_CadastralCost = false;
		}

		//-----SpatialData-------------------------
		if (qName.equalsIgnoreCase("Entity_Spatial") && t_Parcel) {
			t_Entity_Spatial = true;
			entitySpatialDTO = new EntitySpatialDTO();
			entitySpatialDTO.setEntSys(attributes.getValue(ENT_SYS));
			spatialElementDTOs = new ArrayList<SpatialElementDTO>();

		}

		//-----Spatial Element---------------------
		if (qName.equalsIgnoreCase("Spatial_Element") && t_Entity_Spatial) {
			t_Spatial_Element = true;
			spatialElementDTO = new SpatialElementDTO();
			spatialElementUnitDTOs = new ArrayList<SpatialElementUnitDTO>();
		}

		if (qName.equalsIgnoreCase("Spelement_Unit") && t_Spatial_Element) {
			t_Spelement_Unit = true;
			spatialElementUnitDTO = new SpatialElementUnitDTO();
			ordinateDTOs = new ArrayList<OrdinateDTO>();
			spatialElementUnitDTO.setTypeUnit(attributes.getValue(TYPE_UNIT));
			spatialElementUnitDTO.setSuNumb(Integer.parseInt(attributes.getValue(SU_NMB)));
		}

		if (qName.equalsIgnoreCase("Ordinate") && t_Spelement_Unit) {
			t_Ordinate = true;
			ordinateDTO = new OrdinateDTO();
			ordinateDTO.setX(Double.valueOf(attributes.getValue(X)));
			ordinateDTO.setY(Double.valueOf(attributes.getValue(Y)));
			ordinateDTO.setOrdNumber(Integer.valueOf(attributes.getValue(ORD)));
			ordinateDTOs.add(ordinateDTO);
			t_Ordinate = false;
		}

		if (qName.equalsIgnoreCase("Coord_System") && t_Cadastral_Block) {
			t_Coord_System = true;
			coordinateSystemDTO = new CoordinateSystemDTO();
			coordinateSystemDTO.setId(attributes.getValue(CS_ID));
			coordinateSystemDTO.setName(attributes.getValue(NAME_COORD_SYS));
			landResolver.updateCoordinateSystem(coordinateSystemDTO);
			t_Coord_System = false;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (t_Area && qName.endsWith("Area") && !t_AreaIn ) {
			t_Area = false;
		}

//		if (t_AreaIn && qName.endsWith("Area")) {
//			t_AreaIn = false;
//		}

		if (t_Address && qName.endsWith("Address")) {
			landDTO.setAddress(addressDTO);
			t_Address = false;
		}

		if (t_Location && qName.endsWith("Location")) {
			t_Location = false;
		}

		if (t_Right && qName.endsWith("Right")) {
			landRightDTOs.add(landRightDTO);
			t_Right = false;
		}

		if (t_Rights && qName.endsWith("Rights")) {
			landDTO.setRights(landRightDTOs);
			t_Rights = false;
		}

		if (t_Spelement_Unit && qName.endsWith("Spelement_Unit")) {
			spatialElementUnitDTO.setOrdinates(ordinateDTOs);
			spatialElementUnitDTOs.add(spatialElementUnitDTO);
			t_Spelement_Unit = false;
		}

		if (t_Spatial_Element && qName.endsWith("Spatial_Element")) {
			spatialElementDTO.setSpatialElementUnits(spatialElementUnitDTOs);
			spatialElementDTOs.add(spatialElementDTO);
			t_Spatial_Element = false;
		}

		if (t_Entity_Spatial && qName.endsWith("Entity_Spatial")) {
			entitySpatialDTO.setSpatialElements(spatialElementDTOs);
			landDTO.setEntitySpatial(entitySpatialDTO);
			t_Entity_Spatial = false;
		}

		if (t_Parcel && qName.endsWith("Parcel")) {
			landResolver.resolve(landDTO);
			t_Parcel = false;
		}

		if (t_Parcels && qName.endsWith("Parcels")) {
			t_Parcels = false;
		}

		if (t_Cadastral_Block && qName.endsWith("Cadastral_Block")) {
			t_Cadastral_Block = false;
		}

		if (t_Cadastral_Blocks && qName.endsWith("Cadastral_Blocks")) {
			t_Cadastral_Blocks = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		//----------AREA------------
		if (t_Area && t_AreaIn) {
			landDTO.setArea(Double.parseDouble(String.valueOf(ch, start, length)));
			t_AreaIn = false;
		}

		if (t_Area && t_Unit) {
			landDTO.setAreaUnit(String.valueOf(ch, start, length));
			t_Unit = false;
		}
		//----------LOCATION---------
		if (t_inBounds) {
			landDTO.setLocationInBounds(String.valueOf(ch, start, length));
			t_inBounds = false;
		}

		if (t_Placed) {
			landDTO.setLocationPlaced(String.valueOf(ch, start, length));
			t_Placed = false;
		}

		//----------ADDRESS-----------
		if (t_Code_OKATO) {
			addressDTO.setOkato(String.valueOf(ch, start, length));
			t_Code_OKATO = false;
		}
		if (t_Code_KLADR) {
			addressDTO.setKladr(String.valueOf(ch, start, length));
			t_Code_KLADR = false;
		}
		if (t_Region) {
			addressDTO.setRegion(String.valueOf(ch, start, length));
			t_Region = false;
		}
		if (t_Note) {
			addressDTO.setNote(String.valueOf(ch, start, length));
			t_Note = false;
		}

		//----------RIGHTS---------
		if (t_Name_R) {
			landRightDTO.setName(String.valueOf(ch, start, length));
			t_Name_R = false;
		}

		if (t_Type_R) {
			landRightDTO.setType(String.valueOf(ch, start, length));
			t_Type_R = false;
		}

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
