package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */
public class CapitalConstructionTargetDecorator implements TargetDecorator<CapitalConstruction> {
	private CapitalConstruction capitalConstruction;

	@Override
	public CapitalConstructionTargetDecorator wrap(CapitalConstruction capitalConstruction) {
		this.capitalConstruction = capitalConstruction;
		return null;
	}

	@Override
	public Long getId() {
		return capitalConstruction.getId();
	}
}
