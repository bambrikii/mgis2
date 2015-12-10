package ru.sovzond.mgis2.web.isogd;

import ru.sovzond.mgis2.Sortable;
import ru.sovzond.mgis2.business.CRUDBeanBase;

/**
 * Created by Alexander Arakelyan on 10.12.15.
 */
public class SwapManager {
	public static <T extends Sortable> void byOrder(SwapIdPair pair, CRUDBeanBase<T> bean) {
		Long sourceId = pair.getSourceId();
		Long targetId = pair.getTargetId();
		T source = bean.load(sourceId);
		T target = bean.load(targetId);
		Long sourceOrder = (source.getSortOrder() == null || source.getSortOrder() == 0) ? sourceId : source.getSortOrder();
		Long targetOrder = (target.getSortOrder() == null || target.getSortOrder() == 0) ? targetId : target.getSortOrder();

		source.setSortOrder(targetOrder);
		target.setSortOrder(sourceOrder);
		bean.save(source);
		bean.save(target);
	}
}
