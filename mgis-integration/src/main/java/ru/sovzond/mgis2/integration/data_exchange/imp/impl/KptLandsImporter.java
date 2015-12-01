package ru.sovzond.mgis2.integration.data_exchange.imp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.integration.data_exchange.imp.LandImportResolverBean;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportRecord;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 01/12/15.
 */
@Service
public class KptLandsImporter extends LandImportBase {

	@Autowired
	private LandImportResolverBean landImportResolverBean;

	@Override
	public List<ReportRecord> imp(InputStream inputStream) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
