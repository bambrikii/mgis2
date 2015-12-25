package ru.sovzond.mgis2.integration.data_exchange.imp.handlers.kpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.BuildingResolver;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.IncompleteConstructResolver;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.BuildingResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.IncompleteConstructResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.LandResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.LandResolver;
import ru.sovzond.mgis2.integration.data_exchange.imp.handlers.KptHandler;
import ru.sovzond.mgis2.integration.data_exchange.imp.impl.LandImportBase;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportRecord;

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

	@Autowired
	private IncompleteConstructResolverBean incompleteConstructResolverBean;

	@Override
	public List<ReportRecord> imp(InputStream inputStream) {
		LandResolver landResolver = new LandResolver(landImportResolverBean);
		BuildingResolver buildingResolver = new BuildingResolver(buildingResolverBean);
		IncompleteConstructResolver incompleteConstructResolver = new IncompleteConstructResolver(incompleteConstructResolverBean);
		doImport(inputStream, new KptHandler(landResolver, buildingResolver, incompleteConstructResolver));
		return landResolver.getReports();
	}
}
