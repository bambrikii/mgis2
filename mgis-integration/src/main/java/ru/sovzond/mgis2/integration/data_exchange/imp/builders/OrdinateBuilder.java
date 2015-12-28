package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.AttributeValueExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilder;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilderEndEvent;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.OrdinateDTO;

import java.util.function.Predicate;

import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.*;

/**
 * Created by Alexander Arakelyan on 25.12.15.
 */
public class OrdinateBuilder extends NodeBuilder<OrdinateDTO> {

	private Integer ordNumber;
	private Double x;
	private Double y;

	public OrdinateBuilder(NodeBuilder parent, Predicate<String> ordinatePredicate, NodeBuilderEndEvent<OrdinateDTO> endEvent) {
		super(parent, ordinatePredicate, endEvent);
	}

	@Override
	protected void extractAttributes(AttributeValueExtractor attributeValueExtractor) {
		ordNumber = Integer.parseInt(attributeValueExtractor.attribute(ORD_ATTR));
		x = Double.parseDouble(attributeValueExtractor.attribute(X_ATTR));
		y = Double.parseDouble(attributeValueExtractor.attribute(Y_ATTR));
	}

	@Override
	protected OrdinateDTO buildImpl() {
		OrdinateDTO ordinateDTO = new OrdinateDTO();
		ordinateDTO.setOrdNumber(ordNumber);
		ordinateDTO.setX(x);
		ordinateDTO.setY(y);
		return ordinateDTO;
	}

	@Override
	protected void resetImpl() {
		super.resetImpl();
		ordNumber = null;
		x = null;
		y = null;
	}
}
