package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.HeirarchialNodeBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.StringNodeBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.AddressDTO;

import java.util.function.Predicate;

/**
 * Created by Alexander Arakelyan on 24.12.15.
 */
public class AddressBuilder extends HeirarchialNodeBuilder<AddressDTO> {

	public final StringNodeBuilder okato;
	public final StringNodeBuilder kladr;
	public final StringNodeBuilder region;
	public final AddressElementBuilder district;
	public final AddressElementBuilder locality;
	public final AddressElementBuilder street;
	public final AddressLevel1Builder level1;
	public final StringNodeBuilder note;

	public AddressBuilder(NodeBuilder parent,
						  Predicate<String> addressPredicate,
						  Predicate<String> okatoPredicate,
						  Predicate<String> kladrPredicate,
						  Predicate<String> regionPredicate,
						  Predicate<String> districtPredicate,
						  Predicate<String> localityPredicate,
						  Predicate<String> streetPredicate,
						  Predicate<String> level1Predicate,
						  Predicate<String> notePredicate
	) {
		super(parent, addressPredicate);
		okato = new StringNodeBuilder(this, okatoPredicate);
		kladr = new StringNodeBuilder(this, kladrPredicate);
		region = new StringNodeBuilder(this, regionPredicate);
		district = new AddressElementBuilder(this, districtPredicate);
		locality = new AddressElementBuilder(this, localityPredicate);
		street = new AddressElementBuilder(this, streetPredicate);
		level1 = new AddressLevel1Builder(this, level1Predicate);
		note = new StringNodeBuilder(this, notePredicate);
	}

	@Override
	public AddressDTO buildImpl() {
		AddressDTO addressDTO = new AddressDTO();

		addressDTO.setOkato(okato.build());
		addressDTO.setKladr(kladr.build());
		addressDTO.setRegion(this.region.build());

		String[] district = this.district.build();
		if (district != null) {
			addressDTO.setDistrictName(district[0]);
			addressDTO.setDistrictType(district[1]);
		}

		String[] locality = this.locality.build();
		if (locality != null) {
			addressDTO.setLocalityName(locality[0]);
			addressDTO.setLocalityType(locality[1]);
		}

		String[] street = this.street.build();
		if (street != null) {
			addressDTO.setStreetName(street[0]);
			addressDTO.setStreetType(street[1]);
		}

		String[] level1 = this.level1.build();
		if (level1 != null) {
			addressDTO.setLevelType(level1[0]);
			addressDTO.setLevelValue(level1[1]);
		}

		addressDTO.setNote(note.build());

		return addressDTO;
	}

	@Override
	protected NodeBuilder[] children() {
		return new NodeBuilder[]{kladr, region, district, locality, street, level1, note};
	}

}
