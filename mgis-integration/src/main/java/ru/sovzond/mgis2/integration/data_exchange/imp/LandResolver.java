package ru.sovzond.mgis2.integration.data_exchange.imp;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportFactory;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportRecord;
import ru.sovzond.mgis2.lands.Land;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexander Arakelyan on 26.11.15.
 */
public class LandResolver implements ILandResolver {
	private Map<String, List<Long>> ids = new HashMap<>();
	private LandImportResolverBean landImportResolverBean;
	private List<ReportRecord> reports = new ArrayList<>();

	public LandResolver(LandImportResolverBean landImportResolverBean) {
		this.landImportResolverBean = landImportResolverBean;
	}

	@Override
	public void resolve(LandDTO land) {
		try {
			Land resolvedLand = landImportResolverBean.resolveLand(land);
			if (land.getEntitySpatial() != null) {
				String entSys = land.getEntitySpatial().getEntSys();
				if (!ids.containsKey(entSys)) {
					ids.put(entSys, new ArrayList<>());
				}
				ids.get(entSys).add(resolvedLand.getId());
			}
			reports.add(ReportFactory.success(land.getCadastralNumber()));
		} catch (Exception ex) {
			reports.add(ReportFactory.error(land.getCadastralNumber(), ex));
		}
	}

	@Override
	public void updateCoordinateSystem(CoordinateSystemDTO coordinateSystemDTO) {
		if (ids.containsKey(coordinateSystemDTO.getId())) {
			for (Long id : ids.get(coordinateSystemDTO.getId())) {
				try {
					landImportResolverBean.updateCoordinateSystem(id, coordinateSystemDTO);
					reports.add(ReportFactory.success(id + ", coordinate: " + coordinateSystemDTO.getName()));
				} catch (Exception ex) {
					reports.add(ReportFactory.error(id + ", coordinate: " + coordinateSystemDTO.getName(), ex));
				}
			}
		}
	}

	public List<ReportRecord> getReports() {
		return reports;
	}


}
