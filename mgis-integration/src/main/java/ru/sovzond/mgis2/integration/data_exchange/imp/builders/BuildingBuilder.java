package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilderEndEvent;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.BuildingDTO;

import java.util.function.Predicate;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public class BuildingBuilder extends ConstructBuilder<BuildingDTO> {

	public BuildingBuilder(
			Predicate<String> buildingPredicate,
			Predicate<String> assignationBuilding,
			Predicate<String> objectTypePredicate,
			Predicate<String> area,
			Predicate<String> address,
			Predicate<String> okato,
			Predicate<String> kladr,
			Predicate<String> region,
			Predicate<String> district,
			Predicate<String> locality,
			Predicate<String> street,
			Predicate<String> level1,
			Predicate<String> note,
			Predicate<String> cadastralCost,
			Predicate<String> entitySpatial,
			Predicate<String> spatialElement,
			Predicate<String> spelementUnit,
			Predicate<String> ordinate,
			NodeBuilderEndEvent<BuildingDTO> endEvent
	) {
		super(
				buildingPredicate,
				assignationBuilding,
				objectTypePredicate,
				area,
				address,
				okato,
				kladr,
				region,
				district,
				locality,
				street,
				level1,
				note,
				cadastralCost,
				entitySpatial,
				spatialElement,
				spelementUnit,
				ordinate,
				endEvent
		);
	}

	@Override
	public BuildingDTO buildImpl() {
		BuildingDTO constructDTO = new BuildingDTO();

		constructDTO.setCadastralNumber(cadastralNumber);

		constructDTO.setArea(area.build());

		constructDTO.setObjectType(objectType.build());

		constructDTO.setAssignationBuilding(assignationBuilding.build());

		constructDTO.setAddress(address.build());

		Number[] cadastralCost = this.cadastralCost.build();
		constructDTO.setCadastralCostValue((Double) cadastralCost[0]);
		constructDTO.setCadastralCostUnit((Integer) cadastralCost[1]);

		constructDTO.setEntitySpatial(entitySpatial.build());

		return constructDTO;
	}
}
