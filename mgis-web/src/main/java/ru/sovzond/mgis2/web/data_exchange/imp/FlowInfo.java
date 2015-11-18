package ru.sovzond.mgis2.web.data_exchange.imp;

import java.io.File;
import java.util.HashSet;

/**
 * by fanxu
 */
public class FlowInfo {

	public int flowChunkSize;
	public long flowTotalSize;
	public String flowIdentifier;
	public String flowFilename;
	public String flowRelativePath;

	public static class FlowChunkNumber {
		public FlowChunkNumber(int number) {
			this.number = number;
		}

		public int number;

		@Override
		public boolean equals(Object obj) {
			return obj instanceof FlowChunkNumber ? ((FlowChunkNumber) obj).number == this.number : false;
		}

		@Override
		public int hashCode() {
			return number;
		}
	}

	/**
	 * Chunks uploaded.
	 */
	public HashSet<FlowChunkNumber> uploadedChunks = new HashSet<>();

	public String flowFilePath;

	public boolean isValid() {
		return (flowChunkSize > 0 && flowTotalSize > 0 && !(HttpUtils.isEmpty(flowIdentifier) || HttpUtils.isEmpty(flowFilename) || HttpUtils.isEmpty(flowRelativePath)));
	}

	public boolean checkIfUploadFinished() {
		int count = (int) Math.ceil(((double) flowTotalSize) / ((double) flowChunkSize));
		for (int i = 1; i < count + 1; i++) {
			if (!uploadedChunks.contains(new FlowChunkNumber(i))) {
				return false;
			}
		}

		//Upload finished, change filename.
		File file = new File(flowFilePath);
		String newPath = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - ".temp".length());
		file.renameTo(new File(newPath));
		return true;
	}
}
