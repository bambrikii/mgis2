package ru.sovzond.mgis2.integration.data_exchange.imp.handlers;

import ru.sovzond.mgis2.integration.data_exchange.imp.ILandResolver;

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
public class Region_CadastrHandler extends RusRegisterHandlerBase {

	public Region_CadastrHandler(ILandResolver landResolver) {
		super(landResolver, Region_CadastrHandler.class.getSimpleName());
	}

}
