package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.AttributeValueExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.HeirarchialNodeBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.EntitySpatialDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.SpatialElementDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.ENT_SYS_ATTR;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public class EntitySpatialBuilder extends HeirarchialNodeBuilder<EntitySpatialDTO> {

	private final List<SpatialElementDTO> spatialElements = new ArrayList<>();

	public final SpatialElementBuilder spatialElement;
	private String entSys;

	public EntitySpatialBuilder(NodeBuilder parent, Predicate<String> entitySpatialPredicate, Predicate<String> spatialElementPredicate, Predicate<String> spelementUnitPredicate, Predicate<String> ordinatePredicate) {
		super(parent, entitySpatialPredicate);
		spatialElement = new SpatialElementBuilder(this, spatialElementPredicate, spelementUnitPredicate, ordinatePredicate, builder -> spatialElements.add(builder.build()));
	}

	public EntitySpatialBuilder end() {
		if (isValid()) {
			setInvalid();
		}
		return this;
	}

	@Override
	public EntitySpatialDTO build() {
		EntitySpatialDTO entitySpatialDTO = new EntitySpatialDTO();
		entitySpatialDTO.setEntSys(entSys);
		entitySpatialDTO.setSpatialElements(new ArrayList<>(spatialElements));
		return entitySpatialDTO;
	}

	@Override
	protected boolean startCascade(String qName, AttributeValueExtractor attributeValueExtractor) {
		return spatialElement.start(qName, attributeValueExtractor);
	}

	@Override
	protected boolean endCascade(String qName) {
		return spatialElement.end(qName);
	}

	@Override
	protected boolean contentCascade(Object content) {
		return spatialElement.content(content);
	}

	@Override
	public void extractAttributes(AttributeValueExtractor attributeValueExtractor) {
		entSys = (String) attributeValueExtractor.attribute(ENT_SYS_ATTR);
	}

	@Override
	public void reset() {
		spatialElements.clear();
		spatialElement.reset();
		entSys = null;
	}
}
