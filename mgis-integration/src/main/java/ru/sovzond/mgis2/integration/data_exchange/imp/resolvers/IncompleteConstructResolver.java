package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.capital_constructs.CapitalConstruction;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.BuildingResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.EntitySpatialDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.IncompleteDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportCollector;

/**
 * Created by Alexander Arakelyan on 25/12/15.
 */
public class IncompleteConstructResolver extends ResolverBase<IncompleteDTO> {
	private SourceDecorator<IncompleteDTO> sourceDecorator;
	private ConstructionTargetDecorator targetDecorator;
	private BuildingResolverBean buildingResolverBean;

	private class IncompleteConstructSourceResolver implements SourceDecorator<IncompleteDTO> {
		private IncompleteDTO dto;

		@Override
		public SourceDecorator wrap(IncompleteDTO dto) {
			this.dto = dto;
			return this;
		}

		@Override
		public String getName() {
			return dto.getCadastralNumber();
		}

		@Override
		public EntitySpatialDTO getEntitySpatial() {
			return dto.getEntitySpatial();
		}
	}

	public IncompleteConstructResolver(BuildingResolverBean buildingResolverBean, ReportCollector reportCollector) {
		super(reportCollector);
		this.buildingResolverBean = buildingResolverBean;
		sourceDecorator = new IncompleteConstructSourceResolver();
		targetDecorator = new ConstructionTargetDecorator();
	}

	@Override
	protected SourceDecorator wrapSource(IncompleteDTO land) {
		return sourceDecorator.wrap(land);
	}

	@Override
	protected TargetDecorator resolveImpl(IncompleteDTO obj) {
		CapitalConstruction capitalConstruction = buildingResolverBean.resolve(obj);
		return targetDecorator.wrap(capitalConstruction);
	}

	@Override
	protected void updateCoordinateSystem(Long id, CoordinateSystemDTO coordinateSystemDTO) {
		buildingResolverBean.updateCoordinateSystem(id, coordinateSystemDTO);
	}
}
