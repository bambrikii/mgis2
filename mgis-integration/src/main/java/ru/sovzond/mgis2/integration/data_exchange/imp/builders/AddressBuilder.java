package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.AttributeValueExtractor;
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
	public AddressDTO build() {
		AddressDTO addressDTO = new AddressDTO();

		addressDTO.setOkato(okato.build());
		addressDTO.setKladr(kladr.build());
		addressDTO.setRegion(this.region.build());

		String[] district = this.district.build();
		addressDTO.setDistrictName(district[0]);
		addressDTO.setDistrictType(district[1]);

		String[] locality = this.locality.build();
		addressDTO.setLocalityName(locality[0]);
		addressDTO.setLocalityType(locality[1]);

		String[] street = this.street.build();
		addressDTO.setStreetName(street[0]);
		addressDTO.setStreetType(street[1]);

		String[] level1 = this.level1.build();
		addressDTO.setLevelType(level1[0]);
		addressDTO.setLevelValue(level1[1]);

		addressDTO.setNote(note.build());

		return addressDTO;
	}

	@Override
	protected boolean startCascade(String qName, AttributeValueExtractor attributeValueExtractor) {
		return
				okato.start(qName, attributeValueExtractor)
						|| kladr.start(qName, attributeValueExtractor)
						|| region.start(qName, attributeValueExtractor)
						|| district.start(qName, attributeValueExtractor)
						|| locality.start(qName, attributeValueExtractor)
						|| street.start(qName, attributeValueExtractor)
						|| level1.start(qName, attributeValueExtractor)
						|| note.start(qName, attributeValueExtractor);
	}

	@Override
	protected boolean endCascade(String qName) {
		return okato.end(qName)
				|| kladr.end(qName)
				|| region.end(qName)
				|| district.end(qName)
				|| locality.end(qName)
				|| street.end(qName)
				|| level1.end(qName)
				|| note.end(qName)
				;
	}

	@Override
	protected boolean contentCascade(Object content) {
		return okato.content(content)
				|| kladr.content(content)
				|| region.content(content)
				|| district.content(content)
				|| locality.content(content)
				|| street.content(content)
				|| level1.content(content)
				|| note.content(content)
				;
	}

	@Override
	public void reset() {
		okato.reset();
		kladr.reset();
		district.reset();
		locality.reset();
		street.reset();
		level1.reset();
		note.reset();
	}
}
