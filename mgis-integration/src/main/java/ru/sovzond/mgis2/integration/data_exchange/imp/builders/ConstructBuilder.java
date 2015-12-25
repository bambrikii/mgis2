package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.*;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.ConstructDTO;

import java.util.function.Predicate;

import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.CADASTRAL_NUMBER_ATTR;

/**
 * Created by Alexander Arakelyan on 24.12.15.
 */
public class ConstructBuilder extends NodeBuilder<ConstructDTO> {

	protected String cadastralNumber;
	public final StringNodeBuilder objectType;
	public StringNodeBuilder assignationBuilding;
	public final DoubleNodeBuilder area;
	public final AddressBuilder address;
	public final CadastralCostBuilder cadastralCost;
	public final EntitySpatialBuilder entitySpatial;

	public ConstructBuilder(
			Predicate<String> buildingPredicate,
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
			Predicate<String> ordinatePredicate
	) {
		super(NodeBuilderFactory.createTrue(), buildingPredicate);
		address = new AddressBuilder(this,
				addressPredicate,
				okatoPredicate,
				kladrPredicate,
				regionPredicate,
				districtPredicate,
				localityPredicate,
				streetPredicate,
				level1Predicate,
				notePredicate
		);
		objectType = new StringNodeBuilder(this, objectTypePredicate);
		assignationBuilding = new StringNodeBuilder(this, assignationBuildingPredicate);
		area = new DoubleNodeBuilder(this, areaPredicate);
		cadastralCost = new CadastralCostBuilder(this, cadastralCostPredicate);
		entitySpatial = new EntitySpatialBuilder(this, entitySpatialPredicate, spatialElementPredicate, spelementUnitPredicate, ordinatePredicate);
	}

	@Override
	public void extractAttributes(AttributeValueExtractor attributeValueExtractor) {
		cadastralNumber = (String) attributeValueExtractor.attribute(CADASTRAL_NUMBER_ATTR);
	}

	@Override
	public ConstructDTO build() {

		ConstructDTO constructDTO = new ConstructDTO();

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

	@Override
	public void reset() {
		super.reset();
		cadastralNumber = null;
		objectType.reset();
		assignationBuilding.reset();
		area.reset();
		address.reset();
		cadastralCost.reset();
		entitySpatial.reset();
	}
}
