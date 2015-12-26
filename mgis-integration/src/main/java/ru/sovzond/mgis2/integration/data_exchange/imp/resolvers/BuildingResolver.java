package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.BuildingResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.BuildingDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.ConstructDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportCollector;

/**
 * Created by Alexander Arakelyan on 25/12/15.
 */
public class BuildingResolver extends ResolverBase<BuildingDTO> {
	private SourceDecorator<ConstructDTO> sourceDecorator;
	private TargetDecorator<CapitalConstruction> targetDecorator;
	private BuildingResolverBean buildingResolverBean;

	public BuildingResolver(BuildingResolverBean buildingResolverBean, ReportCollector reportCollector) {
		super(reportCollector);
		this.buildingResolverBean = buildingResolverBean;
		sourceDecorator = new ConstructSourceDecorator();
		targetDecorator = new ConstructionTargetDecorator();
	}

	@Override
	protected SourceDecorator wrapSource(BuildingDTO land) {
		return sourceDecorator.wrap(land);
	}

	@Override
	protected TargetDecorator resolveImpl(BuildingDTO obj) {
		CapitalConstruction capitalConstruction = buildingResolverBean.resolve(obj);
		return targetDecorator.wrap(capitalConstruction);
	}

	@Override
	protected void updateCoordinateSystem(Long id, CoordinateSystemDTO coordinateSystemDTO) {
		buildingResolverBean.updateCoordinateSystem(id, coordinateSystemDTO);
	}
}
