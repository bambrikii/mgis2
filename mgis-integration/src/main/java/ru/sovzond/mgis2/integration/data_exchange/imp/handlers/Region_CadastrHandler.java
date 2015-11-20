package ru.sovzond.mgis2.integration.data_exchange.imp.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ru.sovzond.mgis2.lands.Land;

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

	/**
	 * ********************************************************
	 * тэги файла xml false - закрытый тэг , true - открытый
	 * ********************************************************
	 */

	//-------Parcel attributes name-----
	final static String CADASTRAL_NUMBER = "CadastralNumber";
	final static String NAME = "Name";
	final static String STATE = "State";
	final static String DATE_CR = "DateCreated";
	//-------INIT-----------------------
	final static String TOTAL = "Total";
	final static String UNIT = "Unit";

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
	boolean t_Code_OKATO = false;//-----Address (In Location)
	boolean t_Code_KLADR = false;
	boolean t_Region = false;
	boolean t_District = false;
	boolean t_Locality = false;
	boolean t_Street = false;
	boolean t_Note = false;
	//---------------------------
	boolean t_Category = false;
	boolean t_Utilization = false;
	//---------------------------
	boolean t_Rights = false;//-----Rights
	boolean t_Right = false;
	boolean t_Name = false;
	boolean t_Type = false;
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




	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		super.fatalError(e);
	}
}
