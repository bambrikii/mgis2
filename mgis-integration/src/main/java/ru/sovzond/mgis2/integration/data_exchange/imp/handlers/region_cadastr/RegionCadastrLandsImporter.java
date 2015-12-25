package ru.sovzond.mgis2.integration.data_exchange.imp.handlers.region_cadastr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.BuildingResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.IncompleteConstructResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.beans.LandResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.handlers.Region_CadastrHandler;
import ru.sovzond.mgis2.integration.data_exchange.imp.impl.LandImportBase;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportRecord;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.BuildingResolver;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.IncompleteConstructResolver;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.LandResolver;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
@Service
public class RegionCadastrLandsImporter extends LandImportBase {

	@Autowired
	private LandResolverBean landImportResolverBean;

	@Autowired
	private BuildingResolverBean buildingResolverBean;

	@Autowired
	private IncompleteConstructResolverBean incompleteConstructResolverBean;

	public List<ReportRecord> imp(InputStream inputStream) {
		LandResolver landResolver = new LandResolver(landImportResolverBean);
		BuildingResolver buildingResolver = new BuildingResolver(buildingResolverBean);
		IncompleteConstructResolver incompleteConstructResolver = new IncompleteConstructResolver(incompleteConstructResolverBean);
		doImport(inputStream, new Region_CadastrHandler(landResolver, buildingResolver, incompleteConstructResolver));
		return landResolver.getReports();
	}
}
