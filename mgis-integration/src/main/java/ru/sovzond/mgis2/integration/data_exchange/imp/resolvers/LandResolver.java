package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.integration.data_exchange.imp.beans.LandResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportCollector;
import ru.sovzond.mgis2.lands.Land;

/**
 * Created by Alexander Arakelyan on 26.11.15.
 */
public class LandResolver extends ResolverBase<LandDTO> {
	private LandResolverBean landImportResolverBean;
	private SourceDecorator sourceDecorator = new LandSourceDecorator();
	private LandTargetDecorator targetDecorator = new LandTargetDecorator();

	public LandResolver(LandResolverBean landImportResolverBean, ReportCollector reportCollector) {
		super(reportCollector);
		this.landImportResolverBean = landImportResolverBean;
	}

	@Override
	protected SourceDecorator wrapSource(LandDTO land) {
		return sourceDecorator.wrap(land);
	}

	@Override
	protected void updateCoordinateSystem(Long id, CoordinateSystemDTO coordinateSystemDTO) {
		landImportResolverBean.updateCoordinateSystem(id, coordinateSystemDTO);
	}

	@Override
	protected TargetDecorator resolveImpl(LandDTO landDTO) {
		Land land = landImportResolverBean.resolveLand(landDTO);
		return targetDecorator.wrap(land);
	}

}
