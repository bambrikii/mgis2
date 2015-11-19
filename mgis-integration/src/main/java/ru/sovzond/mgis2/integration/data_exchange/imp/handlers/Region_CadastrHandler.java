package ru.sovzond.mgis2.integration.data_exchange.imp.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 * <p>
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
