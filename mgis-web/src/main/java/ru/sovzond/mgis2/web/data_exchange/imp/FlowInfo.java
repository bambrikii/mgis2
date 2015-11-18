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

	public static class ResumableChunkNumber {
		public ResumableChunkNumber(int number) {
			this.number = number;
		}

		public int number;

		@Override
		public boolean equals(Object obj) {
			return obj instanceof ResumableChunkNumber
					? ((ResumableChunkNumber) obj).number == this.number : false;
		}

		@Override
		public int hashCode() {
			return number;
		}
	}

	//Chunks uploaded
	public HashSet<ResumableChunkNumber> uploadedChunks = new HashSet<ResumableChunkNumber>();

	public String flowFilePath;

	public boolean isVaild() {
		if (flowChunkSize < 0 || flowTotalSize < 0
				|| HttpUtils.isEmpty(flowIdentifier)
				|| HttpUtils.isEmpty(flowFilename)
				|| HttpUtils.isEmpty(flowRelativePath)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkIfUploadFinished() {
		//check if upload finished
		int count = (int) Math.ceil(((double) flowTotalSize) / ((double) flowChunkSize));
		for (int i = 1; i < count + 1; i++) {
			if (!uploadedChunks.contains(new ResumableChunkNumber(i))) {
				return false;
			}
		}

		//Upload finished, change filename.
		File file = new File(flowFilePath);
		String new_path = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - ".temp".length());
		file.renameTo(new File(new_path));
		return true;
	}
}
