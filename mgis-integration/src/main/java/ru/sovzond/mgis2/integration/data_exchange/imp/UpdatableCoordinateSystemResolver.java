package ru.sovzond.mgis2.integration.data_exchange.imp;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;
import ru.sovzond.mgis2.lands.Land;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexander Arakelyan on 26.11.15.
 */
public class UpdatableCoordinateSystemResolver implements ILandResolver {
	private Map<String, List<Long>> ids = new HashMap<>();
	private LandImportResolverBean landImportResolverBean;

	public UpdatableCoordinateSystemResolver(LandImportResolverBean landImportResolverBean) {
		this.landImportResolverBean = landImportResolverBean;
	}

	@Override
	public void resolve(LandDTO land) {
		Land resolvedLand = landImportResolverBean.resolveLand(land);
		if (land.getEntitySpatial() != null) {
			String entSys = land.getEntitySpatial().getEntSys();
			if (!ids.containsKey(entSys)) {
				ids.put(entSys, new ArrayList<>());
			}
			ids.get(entSys).add(resolvedLand.getId());
		}
	}

	@Override
	public void updateCoordinateSystem(CoordinateSystemDTO coordinateSystemDTO) {
		if (ids.containsKey(coordinateSystemDTO.getId())) {
			for (Long id : ids.get(coordinateSystemDTO.getId())) {
				landImportResolverBean.updateCoordinateSystem(id, coordinateSystemDTO);
			}
		}
	}


}
