package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.HierarchicalNodeBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilderEndEvent;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.SpatialElementDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.SpatialElementUnitDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public class SpatialElementBuilder extends HierarchicalNodeBuilder<SpatialElementDTO> {

	private List<SpatialElementUnitDTO> spatialElementUnits = new ArrayList<>();

	public final SpatialElementUnitBuilder spatialElementUnit;

	public SpatialElementBuilder(NodeBuilder parent, Predicate<String> spatialElementPredicate, Predicate<String> spelementUnitPredicate, Predicate<String> ordinatePredicate, NodeBuilderEndEvent<SpatialElementDTO> endEvent) {
		super(parent, spatialElementPredicate, endEvent);
		spatialElementUnit = new SpatialElementUnitBuilder(this, spelementUnitPredicate, ordinatePredicate, builder -> {
			spatialElementUnits.add(builder.build());
			builder.reset();
		});
	}

	@Override
	protected SpatialElementDTO buildImpl() {
		SpatialElementDTO spatialElementDTO = new SpatialElementDTO();
		spatialElementDTO.setSpatialElementUnits(new ArrayList(spatialElementUnits));
		spatialElementUnits.clear();
		return spatialElementDTO;
	}

	@Override
	protected NodeBuilder[] children() {
		return new NodeBuilder[]{spatialElementUnit};
	}

	@Override
	protected void resetImpl() {
		spatialElementUnits.clear();
		spatialElementUnit.resetImpl();
	}
}
