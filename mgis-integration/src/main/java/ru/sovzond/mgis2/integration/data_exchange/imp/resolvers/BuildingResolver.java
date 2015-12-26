package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.BuildingResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.BuildingDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.EntitySpatialDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportCollector;

/**
 * Created by Alexander Arakelyan on 25/12/15.
 */
public class BuildingResolver extends ResolverBase<BuildingDTO> {
	private SourceDecorator<BuildingDTO> sourceDecorator;
	private CapitalConstructionTargetDecorator targetDecorator;
	private BuildingResolverBean buildingResolverBean;

	private class BuildingSourceResolver implements SourceDecorator<BuildingDTO> {
		private BuildingDTO buildingDTO;

		@Override
		public SourceDecorator wrap(BuildingDTO land) {
			this.buildingDTO = land;
			return this;
		}

		@Override
		public String getName() {
			return buildingDTO.getCadastralNumber();
		}

		@Override
		public EntitySpatialDTO getEntitySpatial() {
			return buildingDTO.getEntitySpatial();
		}
	}

	public BuildingResolver(BuildingResolverBean buildingResolverBean, ReportCollector reportCollector) {
		super(reportCollector);
		this.buildingResolverBean = buildingResolverBean;
		sourceDecorator = new BuildingSourceResolver();
		targetDecorator = new CapitalConstructionTargetDecorator();
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
