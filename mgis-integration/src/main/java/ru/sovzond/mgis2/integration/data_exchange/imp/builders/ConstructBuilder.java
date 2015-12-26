package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.*;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.ConstructDTO;

import java.util.function.Predicate;

import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.CADASTRAL_NUMBER_ATTR;

/**
 * Created by Alexander Arakelyan on 24.12.15.
 */
public abstract class ConstructBuilder<T extends ConstructDTO> extends HeirarchialNodeBuilder<T> {

	protected String cadastralNumber;
	public final StringNodeBuilder objectType;
	public StringNodeBuilder assignationBuilding;
	public final DoubleNodeBuilder area;
	public final AddressBuilder address;
	public final CadastralCostBuilder cadastralCost;
	public final EntitySpatialBuilder entitySpatial;

	public ConstructBuilder(
			Predicate<String> constructPredicate,
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
			NodeBuilderEndEvent<T> endEvent
	) {
		super(NodeBuilderFactory.createTrue(), constructPredicate, endEvent);
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
		cadastralNumber = attributeValueExtractor.attribute(CADASTRAL_NUMBER_ATTR);
	}

	@Override
	public abstract T buildImpl();

	@Override
	protected NodeBuilder[] children() {
		return new NodeBuilder[]{objectType, assignationBuilding, area, address, cadastralCost, entitySpatial};
	}

	@Override
	public void resetImpl() {
		super.resetImpl();
		cadastralNumber = null;
	}


}
