package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilderEndEvent;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.IncompleteDTO;

import java.util.function.Predicate;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public class IncompleteBuilder extends ConstructBuilder<IncompleteDTO> {


	public IncompleteBuilder(Predicate<String> buildingPredicate,
							 Predicate<String> assignationBuildingPredicate,
							 Predicate<String> objectTypePredicate,
							 Predicate<String> areaPredicate,
							 Predicate<String> addressPredicate,
							 Predicate<String> okatoPredicate,
							 Predicate<String> kladrPredicate,
							 Predicate<String> regionPredicate,
							 Predicate<String> districtPredicate,
							 Predicate<String> localityPredicate,
							 Predicate<String> streetPredicate,
							 Predicate<String> level1Predicate,
							 Predicate<String> notePredicate,
							 Predicate<String> cadastralCostPredicate,
							 Predicate<String> entitySpatialPredicate,
							 Predicate<String> spatialElementPredicate,
							 Predicate<String> spelementUnitPredicate,
							 Predicate<String> ordinatePredicate,
							 NodeBuilderEndEvent<IncompleteDTO> endEvent
	) {
		super(buildingPredicate,
				assignationBuildingPredicate,
				objectTypePredicate,
				areaPredicate,
				addressPredicate,
				okatoPredicate,
				kladrPredicate,
				regionPredicate,
				districtPredicate,
				localityPredicate,
				streetPredicate,
				level1Predicate,
				notePredicate,
				cadastralCostPredicate,
				entitySpatialPredicate,
				spatialElementPredicate,
				spelementUnitPredicate,
				ordinatePredicate,
				endEvent
		);
	}

	@Override
	protected IncompleteDTO buildImpl() {
		IncompleteDTO constructDTO = new IncompleteDTO();

		constructDTO.setCadastralNumber(cadastralNumber);

		constructDTO.setArea(area.build());

		constructDTO.setObjectType(objectType.build());

		constructDTO.setAssignationBuilding(assignationBuilding.build());

		constructDTO.setAddress(address.build());

		Number[] cadastralCost = this.cadastralCost.build();
		if (cadastralCost != null) {
			constructDTO.setCadastralCostValue((Double) cadastralCost[0]);
			constructDTO.setCadastralCostUnit((Integer) cadastralCost[1]);
		}

		constructDTO.setEntitySpatial(entitySpatial.build());

		return constructDTO;
	}
}
