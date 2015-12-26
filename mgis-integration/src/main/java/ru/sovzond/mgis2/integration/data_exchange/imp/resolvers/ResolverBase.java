package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.CoordinateSystemDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.EntitySpatialDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */
public abstract class ResolverBase<T> implements ILandResolver<T> {
	private Map<String, List<Long>> ids = new HashMap<>();
	private ReportCollector reportCollector;

	protected ResolverBase(ReportCollector reportCollector) {
		this.reportCollector = reportCollector;
	}

	@Override
	public final void resolve(T land) {
		SourceDecorator sourceDecorator = wrapSource(land);
		String name = sourceDecorator.getName();
		try {
			EntitySpatialDTO entitySpatial = sourceDecorator.getEntitySpatial();
			if (entitySpatial != null) {
				String entSys = entitySpatial.getEntSys();
				if (!ids.containsKey(entSys)) {
					ids.put(entSys, new ArrayList<>());
				}
				TargetDecorator<T> targetDecorator = resolveImpl(land);
				ids.get(entSys).add(targetDecorator.getId());
			}
			reportCollector.report(name);
		} catch (Exception ex) {
			reportCollector.report(name, ex);
		}
	}

	protected abstract SourceDecorator wrapSource(T land);

	public final void updateCoordinateSystem(CoordinateSystemDTO coordinateSystemDTO) {
		if (ids.containsKey(coordinateSystemDTO.getId())) {
			for (Long id : ids.get(coordinateSystemDTO.getId())) {
				String message = id + ", coordinate: " + coordinateSystemDTO.getName();
				try {
					updateCoordinateSystem(id, coordinateSystemDTO);
					reportCollector.report(message);
				} catch (Exception ex) {
					reportCollector.report(message, ex);
				}
			}
		}
	}

	protected abstract TargetDecorator<T> resolveImpl(T obj);

	protected abstract void updateCoordinateSystem(Long id, CoordinateSystemDTO coordinateSystem);

}
