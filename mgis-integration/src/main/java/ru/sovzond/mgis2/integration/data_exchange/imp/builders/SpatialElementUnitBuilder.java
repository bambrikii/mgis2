package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.AttributeValueExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.HeirarchialNodeBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilderEndEvent;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.OrdinateDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.SpatialElementUnitDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.SU_NMB_ATTR;
import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.TYPE_UNIT_ATTR;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public class SpatialElementUnitBuilder extends HeirarchialNodeBuilder<SpatialElementUnitDTO> {

	private List<OrdinateDTO> ordinates = new ArrayList<>();
	public final OrdinateBuilder ordinate;
	private Integer suNumb;
	private String typeUnit;

	public SpatialElementUnitBuilder(NodeBuilder parent, Predicate<String> spelementUnitPredicate, Predicate<String> ordinatePredicate, NodeBuilderEndEvent<SpatialElementUnitDTO> endEvent) {
		super(parent, spelementUnitPredicate, endEvent);
		ordinate = new OrdinateBuilder(this, ordinatePredicate, builder -> {
			ordinates.add(builder.build());
			builder.reset();
		});
	}

	@Override
	public SpatialElementUnitDTO build() {
		SpatialElementUnitDTO spatialElementUnitDTO = new SpatialElementUnitDTO();
		spatialElementUnitDTO.setOrdinates(new ArrayList<>(this.ordinates));
		spatialElementUnitDTO.setSuNumb(suNumb);
		spatialElementUnitDTO.setTypeUnit(typeUnit);
		this.ordinates.clear();
		return spatialElementUnitDTO;
	}

	@Override
	public void extractAttributes(AttributeValueExtractor attributeValueExtractor) {
		super.extractAttributes(attributeValueExtractor);
		suNumb = Integer.parseInt(attributeValueExtractor.attribute(SU_NMB_ATTR));
		typeUnit = attributeValueExtractor.attribute(TYPE_UNIT_ATTR);
	}

	@Override
	protected boolean startCascade(String qName, AttributeValueExtractor attributeValueExtractor) {
		return ordinate.start(qName, attributeValueExtractor);
	}

	@Override
	protected boolean endCascade(String qName) {
		return ordinate.end(qName);
	}

	@Override
	protected boolean contentCascade(String content) {
		return ordinate.content(content);
	}

	@Override
	public void reset() {
		ordinates.clear();
		suNumb = null;
		typeUnit = null;
	}
}
