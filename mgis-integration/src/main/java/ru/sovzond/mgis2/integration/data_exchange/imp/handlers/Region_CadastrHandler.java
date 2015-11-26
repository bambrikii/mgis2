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
	List<LandRightDTO> landRightDTOs;


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


		if (qName.equalsIgnoreCase("Parcels")) { //-------------Parcels
			t_Parcels = true;
		}
		if (qName.equalsIgnoreCase("Parcel") && t_Parcels) {
			t_Parcel = true;
			landDTO.setCadastralNumber(attributes.getValue(CADASTRAL_NUMBER));
			landDTO.setName(attributes.getValue(NAME));
			landDTO.setState(attributes.getValue(STATE));
			landDTO.setDateCreated(toDate(attributes.getValue(DATE_CR), dateFormat));
		}
		if (qName.equalsIgnoreCase("Area")) { //-------------Area
			t_Area = true;
		}
		if (qName.equalsIgnoreCase("Area") && t_Area) {
			t_AreaIn = true;
		}
		if (qName.equalsIgnoreCase("Unit") && t_Area) {
			t_Unit = true;
		}
		if (qName.equalsIgnoreCase("Location")) { //--------Location
			t_Location = true;
		}
		if (qName.equalsIgnoreCase("inBounds")) {
			t_inBounds = true;
		}
		if (qName.equalsIgnoreCase("Placed")) {
			t_Placed = true;
		}

		if (qName.equalsIgnoreCase("Address")) { //-------Address
			t_Address = true;
			addressDTO = new AddressDTO();
		}
		if (qName.equalsIgnoreCase("Code_OKATO")) {
			t_Code_OKATO = true;
		}
		if (qName.equalsIgnoreCase("Code_KLADR")) {
			t_Code_KLADR = true;
		}

		if (qName.equalsIgnoreCase("Region")) {
			t_Region = true;
		}
		if (qName.equalsIgnoreCase("District")) {
			t_District = true;
			addressDTO.setDistrictName(attributes.getValue(NAME_ALL));
			addressDTO.setDistrictType(attributes.getValue(TYPE_ALL));
		}
		if (qName.equalsIgnoreCase("Locality")) {
			t_Locality = true;
			addressDTO.setLocalityName(attributes.getValue(NAME_ALL));
			addressDTO.setLocalityType(attributes.getValue(TYPE_ALL));
		}
		if (qName.equalsIgnoreCase("Street")) {
			t_Street = true;
			addressDTO.setStreetName(attributes.getValue(NAME_ALL));
			addressDTO.setStreetType(attributes.getValue(TYPE_ALL));
		}

		if (qName.equalsIgnoreCase("Level1")) {
			t_Level1 = true;
			addressDTO.setLevelType(attributes.getValue(TYPE_ALL));
			addressDTO.setLevelValue(attributes.getValue(VALUE));
		}
		if (qName.equalsIgnoreCase("Note")) {
			t_Note = true;
		}
		if (qName.equalsIgnoreCase("Category")) { //-------Category
			t_Category = true;
			landDTO.setCategory(attributes.getValue(CATEGORY));
		}

		if (qName.equalsIgnoreCase("Utilization")) { //-------Utiliz
			t_Utilization = true;
			landDTO.setUtilizationByDoc(attributes.getValue(UTILIZ));
		}
		if (qName.equalsIgnoreCase("Rights")) { //---------Rights
			t_Rights = true;
		}
		if (qName.equalsIgnoreCase("Right") && t_Rights) {
			t_Right = true;
			landRightDTO = new LandRightDTO();
		}
		if (qName.equalsIgnoreCase("Name")) {
			t_Name_R = true;
		}
		if (qName.equalsIgnoreCase("Type")) {
			t_Type_R = true;
		}
		if (qName.equalsIgnoreCase("CadastralCost")) { //-----------Cadastral cost
			t_CadastralCost = true;
			landDTO.setCadastralCostValue(Double.valueOf(attributes.getValue(CADASTRAL_COST_VALUE)));
			landDTO.setCadastralCostUnit(Integer.valueOf(attributes.getValue(CADASTRAL_COST_UNIT)));
		}
		if (qName.equalsIgnoreCase("SpatialData")) { //-----SpatialData
			t_SpatialData = true;
			entitySpatialDTO = new EntitySpatialDTO();
		}
		if (qName.equalsIgnoreCase("Entity_Spatial")) {
			t_Entity_Spatial = true;
			entitySpatialDTO.setEntSys(attributes.getValue(ENT_SYS));

		}
		if (qName.equalsIgnoreCase("Spatial_Element")) { //-----Spatial Element
			t_Spatial_Element = true;
			spatialElementDTO = new SpatialElementDTO();
		}
		if (qName.equalsIgnoreCase("Spelement_Unit")) {
			//TODO: Не понял что куда закинуть в массивы ordinates из атрибутов
			t_Spelement_Unit = true;
			spatialElementUnitDTO = new SpatialElementUnitDTO();
			attributes.getValue(TYPE_UNIT);
			attributes.getValue(SU_NMB);
		}
		if (qName.equalsIgnoreCase("Ordinate")) {
			t_Ordinate = true;
			ordinateDTO = new OrdinateDTO();
			ordinateDTO.setX(Double.valueOf(attributes.getValue(X)));
			ordinateDTO.setY(Double.valueOf(attributes.getValue(Y)));
			ordinateDTO.setOrdNumber(Integer.valueOf(attributes.getValue(ORD)));

		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (t_Address && qName.endsWith("Address")) {
			landDTO.setAddress(addressDTO);
			t_Address = false;
		}

		if (t_Right && qName.endsWith("Right")) {
			t_Right = false;
			//landRightDTOs
		}

		if (t_Rights && qName.endsWith("Rights")) {
			landDTO.setRights(landRightDTOs);
			t_Rights = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (t_Area && t_AreaIn) {
			landDTO.setArea(Double.parseDouble(String.valueOf(ch, start, length)));
			t_AreaIn = false;
		}
		if (t_Area && t_Unit) {
			landDTO.setAreaUnit(String.valueOf(ch, start, length));
			t_Unit = false;
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
