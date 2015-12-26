package ru.sovzond.mgis2.integration.data_exchange.imp.handlers.kpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.BuildingResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.LandResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.handlers.KptHandler;
import ru.sovzond.mgis2.integration.data_exchange.imp.impl.LandImportBase;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportCollector;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportRecord;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.BuildingResolver;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.IncompleteConstructResolver;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.LandResolver;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 01/12/15.
 */
@Service
public class KptLandsImporter extends LandImportBase {

	@Autowired
	private LandResolverBean landImportResolverBean;

	@Autowired
	private BuildingResolverBean buildingResolverBean;

	@Override
	public List<ReportRecord> imp(InputStream inputStream) {
		ReportCollector reportCollector = new ReportCollector();
		LandResolver landResolver = new LandResolver(landImportResolverBean, reportCollector);
		BuildingResolver buildingResolver = new BuildingResolver(buildingResolverBean, reportCollector);
		IncompleteConstructResolver incompleteConstructResolver = new IncompleteConstructResolver(buildingResolverBean, reportCollector);
		doImport(inputStream, new KptHandler(landResolver, buildingResolver, incompleteConstructResolver));
		return reportCollector.getReports();
	}
}
