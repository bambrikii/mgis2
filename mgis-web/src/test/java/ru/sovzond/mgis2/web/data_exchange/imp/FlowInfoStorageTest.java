package ru.sovzond.mgis2.web.data_exchange.imp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 */
@RunWith(Parameterized.class)
public class FlowInfoStorageTest {

	@Parameterized.Parameters(name = "{index}: testLoadComplete({0}, {1})")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][]{{10, 9}, {10, 31}, {10, 35}, {10, 39}});
	}

	String flowIdentifier = "123";
	String flowFilename = "123";
	String flowRelativePath = "./";
	String flowFilePath = "./";
	private int flowChunkSize;
	private long flowTotalSize;
	private FlowInfoStorage instance;
	private FlowInfo info;

	public FlowInfoStorageTest(int flowChunkSize, long flowTotalSize) {
		this.flowChunkSize = flowChunkSize;
		this.flowTotalSize = flowTotalSize;
	}


	@Before
	public void before() {
		instance = FlowInfoStorage.getInstance();
		info = instance.writeFlowInfoIfNone(flowChunkSize, flowTotalSize, flowIdentifier, flowFilename, flowRelativePath, flowFilePath);
	}

	@After
	public void after() {
		instance.remove(info);
	}

	@Test
	public void testLoadComplete() {
		Assert.assertNotNull(info);
		Assert.assertNotNull(instance.readFlowInfo(info.getFlowIdentifier()));
		int i;
		long l = flowTotalSize / flowChunkSize;

		for (i = 0; i < l; i++) {
			Assert.assertFalse(info.containsChunk(i + 1));
		}

		for (i = 0; i < l; i++) {
			info.addChunk(i + 1, i * flowChunkSize, (i + 1) * flowChunkSize);
			Assert.assertTrue(info.containsChunk(i + 1));
			Assert.assertFalse(info.checkIfUploadComplete());
		}

		info.addChunk(i + 1, i * flowChunkSize, flowTotalSize);
		Assert.assertTrue(info.containsChunk(i + 1));
		Assert.assertTrue(info.checkIfUploadComplete());
		instance.remove(info);
		Assert.assertNull(instance.readFlowInfo(info.getFlowIdentifier()));
	}
}
