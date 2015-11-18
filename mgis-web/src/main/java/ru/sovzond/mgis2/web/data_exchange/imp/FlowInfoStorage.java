package ru.sovzond.mgis2.web.data_exchange.imp;

import java.util.HashMap;

/**
 * by fanxu
 * <p>
 * https://github.com/flowjs/flow.js/tree/master/samples/java/src/resumable/js/upload
 */
public class FlowInfoStorage {

	//Single instance
	private FlowInfoStorage() {
	}

	private static FlowInfoStorage sInstance;

	public static synchronized FlowInfoStorage getInstance() {
		if (sInstance == null) {
			sInstance = new FlowInfoStorage();
		}
		return sInstance;
	}

	//flowIdentifier --  ResumableInfo
	private HashMap<String, FlowInfo> mMap = new HashMap<String, FlowInfo>();

	/**
	 * Get ResumableInfo from mMap or Create a new one.
	 *
	 * @param flowChunkSize
	 * @param flowTotalSize
	 * @param flowIdentifier
	 * @param flowFilename
	 * @param flowRelativePath
	 * @param flowFilePath
	 * @return
	 */
	public synchronized FlowInfo getFlowInfo(int flowChunkSize, long flowTotalSize,
											 String flowIdentifier, String flowFilename,
											 String flowRelativePath, String flowFilePath) {

		FlowInfo info = mMap.get(flowIdentifier);

		if (info == null) {
			info = new FlowInfo();

			info.flowChunkSize = flowChunkSize;
			info.flowTotalSize = flowTotalSize;
			info.flowIdentifier = flowIdentifier;
			info.flowFilename = flowFilename;
			info.flowRelativePath = flowRelativePath;
			info.flowFilePath = flowFilePath;

			mMap.put(flowIdentifier, info);
		}
		return info;
	}

	/**
	 * É¾³ýResumableInfo
	 *
	 * @param info
	 */
	public void remove(FlowInfo info) {
		mMap.remove(info.flowIdentifier);
	}
}
