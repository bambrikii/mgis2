package ru.sovzond.mgis2.integration.data_exchange.imp.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.integration.data_exchange.imp.LandImportResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.LandResolver;
import ru.sovzond.mgis2.integration.data_exchange.imp.handlers.Region_CadastrHandler;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportRecord;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
@Service
public class RegionCadastrLandsImporter extends LandImportBase {

	@Autowired
	private LandImportResolverBean landImportResolverBean;

	public List<ReportRecord> imp(InputStream inputStream) {
		LandResolver landResolver = new LandResolver(landImportResolverBean);
		doImport(inputStream, new Region_CadastrHandler(landResolver));
		return landResolver.getReports();
	}
}
