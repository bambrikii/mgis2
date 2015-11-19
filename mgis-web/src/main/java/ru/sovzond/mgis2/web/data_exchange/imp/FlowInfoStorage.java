package ru.sovzond.mgis2.web.data_exchange.imp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * by fanxu
 * <p>
 * https://github.com/flowjs/flow.js/tree/master/samples/java/src/resumable/js/upload
 */
public class FlowInfoStorage {

	//Single instance
	private FlowInfoStorage() {
	}

	private static volatile FlowInfoStorage sInstance;

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public static synchronized FlowInfoStorage getInstance() {
		if (sInstance == null) {
			sInstance = new FlowInfoStorage();
		}
		return sInstance;
	}

	// flowIdentifier --  FlowInfo
	private Map<String, FlowInfo> flowInfoMap = new ConcurrentHashMap<>();

	public FlowInfo readFlowInfo(String flowIdentifier) {
		FlowInfo info = null;
		ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
		try {
			readLock.lock();
			info = flowInfoMap.get(flowIdentifier);
		} finally {
			readLock.unlock();
		}
		return info;
	}

	/**
	 * Get FlowInfo from flowInfoMap or Create a new one.
	 *
	 * @param flowChunkSize
	 * @param flowTotalSize
	 * @param flowIdentifier
	 * @param flowFilename
	 * @param flowRelativePath
	 * @param flowFilePath
	 * @return
	 */
	public FlowInfo writeFlowInfoIfNone(int flowChunkSize, long flowTotalSize,
										String flowIdentifier, String flowFilename,
										String flowRelativePath, String flowFilePath) {

//		ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
		ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
		FlowInfo info = flowInfoMap.get(flowIdentifier);
		if (info == null) {
			try {
				writeLock.lock();
				info = flowInfoMap.get(flowIdentifier);
				if (info == null) {
					info = new FlowInfo(
							flowChunkSize,
							flowTotalSize,
							flowIdentifier,
							flowFilename,
							flowRelativePath,
							flowFilePath);
					flowInfoMap.put(flowIdentifier, info);
				}
			} finally {
				writeLock.unlock();
			}
		}
		return info;
	}

	/**
	 * É¾³ýResumableInfo
	 *
	 * @param info
	 */
	public void remove(FlowInfo info) {
		flowInfoMap.remove(info.getFlowIdentifier());
	}
}
