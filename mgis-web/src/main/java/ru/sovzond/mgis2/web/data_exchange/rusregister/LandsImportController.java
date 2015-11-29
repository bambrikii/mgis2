package ru.sovzond.mgis2.web.data_exchange.rusregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.sovzond.mgis2.integration.data_exchange.imp.LandsImporter;
import ru.sovzond.mgis2.integration.data_exchange.imp.report.ReportRecord;
import ru.sovzond.mgis2.web.data_exchange.imp.FlowInfo;
import ru.sovzond.mgis2.web.data_exchange.imp.UploadControllerBase;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 17.11.15.
 */
@Controller
public class LandsImportController extends UploadControllerBase {

	@Autowired
	private LandsImporter landsImporter;

	@RequestMapping(value = "/data-exchange/import/rusregister/lands", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String rusRegisterKPT(@RequestParam(FLOW_CHUNK_NUMBER) int flowChunkNumber, //
								 @RequestParam(FLOW_CHUNK_SIZE) int flowChunkSize, //
								 @RequestParam(FLOW_TOTAL_SIZE) long flowTotalSize, //
								 @RequestParam(FLOW_IDENTIFIER) String flowIdentifier, //
								 @RequestParam(FLOW_FILENAME) String flowFileName, //
								 @RequestParam(FLOW_RELATIVE_PATH) String flowRelativePath, //
								 @RequestParam(FLOW_FILE) MultipartFile file //
	) {
		FlowInfo info = writeFlowInfo(flowChunkSize, flowTotalSize, flowIdentifier, flowFileName, flowRelativePath);
		return processStream(flowChunkNumber, info, file, inputStream -> {
			List<ReportRecord> result = landsImporter.imp(inputStream);
			StringBuilder sb = new StringBuilder();
			for (ReportRecord record : result) {
				sb.append(record.getIdentifier()).append(", ").append(record.getMessage()).append(", ").append(record.getOutcome()).append("\n");
			}
			return sb.toString();
		});
	}

	@RequestMapping(value = "/data-exchange/import/rusregister/lands", method = RequestMethod.GET, consumes = "text/plain", produces = "text/plain")
	protected ResponseEntity<String> rusRegisterKPT(@RequestParam(FLOW_CHUNK_NUMBER) int flowChunkNumber, //
													@RequestParam(FLOW_IDENTIFIER) String flowIdentifier //
	) {
		StringBuilder result = new StringBuilder();
		FlowInfo info = readFlowInfo(flowIdentifier);
		if (info != null && info.containsChunk(flowChunkNumber)) {
			result.append("Uploaded."); //This Chunk has been Uploaded.
			return new ResponseEntity<>(result.toString(), HttpStatus.OK);
		} else {
			result.append("The chunk ").append(flowChunkNumber).append(" of ").append(flowIdentifier).append(" not found.");
			return new ResponseEntity<>(result.toString(), HttpStatus.NOT_FOUND);
		}
	}

}
