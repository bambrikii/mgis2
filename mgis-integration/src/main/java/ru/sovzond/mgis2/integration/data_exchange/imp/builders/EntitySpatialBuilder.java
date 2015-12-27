package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.AttributeValueExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.HierarchicalNodeBuilder;
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
public class EntitySpatialBuilder extends HierarchicalNodeBuilder<EntitySpatialDTO> {

	private final List<SpatialElementDTO> spatialElements = new ArrayList<>();
	private final SpatialElementBuilder spatialElement;
	private String entSys;

	public EntitySpatialBuilder(NodeBuilder parent, Predicate<String> entitySpatialPredicate, Predicate<String> spatialElementPredicate, Predicate<String> spelementUnitPredicate, Predicate<String> ordinatePredicate) {
		super(parent, entitySpatialPredicate);
		spatialElement = new SpatialElementBuilder(this, spatialElementPredicate, spelementUnitPredicate, ordinatePredicate, builder -> {
			spatialElements.add(builder.build());
			builder.reset();
		});
	}

	public EntitySpatialBuilder end() {
		if (isActive()) {
			setInactive();
		}
		return this;
	}

	@Override
	public EntitySpatialDTO buildImpl() {
		EntitySpatialDTO entitySpatialDTO = new EntitySpatialDTO();
		entitySpatialDTO.setEntSys(entSys);
		entitySpatialDTO.setSpatialElements(new ArrayList<>(spatialElements));
		return entitySpatialDTO;
	}

	@Override
	public void extractAttributes(AttributeValueExtractor attributeValueExtractor) {
		entSys = attributeValueExtractor.attribute(ENT_SYS_ATTR);
	}

	@Override
	protected NodeBuilder[] children() {
		return new NodeBuilder[]{spatialElement};
	}

	@Override
	public void resetImpl() {
		spatialElements.clear();
		entSys = null;
	}
}
