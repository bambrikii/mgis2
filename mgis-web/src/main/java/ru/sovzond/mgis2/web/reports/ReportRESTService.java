package ru.sovzond.mgis2.web.reports;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.registers.persons.LegalPerson;
import ru.sovzond.mgis2.reports.Report;
import ru.sovzond.mgis2.reports.ReportBean;
import ru.sovzond.mgis2.reports.ReportManagerException;
import ru.sovzond.mgis2.reports.ReportOutputFormat;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Alexander Arakelyan on 11/12/15.
 */

@RestController
@RequestMapping("/reports")
@Scope("session")
public class ReportRESTService implements Serializable {

	@Autowired
	private ReportBean reportBean;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<Report> list(@RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "orderBy", defaultValue = "") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
		return reportBean.list(orderBy, first, max);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public Report save(@PathVariable("id") Long id, @RequestBody LegalPerson legalPerson) {
		Report report;
		if (id == 0) {
			report = new Report();
		} else {
			report = reportBean.load(id);
		}
		BeanUtils.copyProperties(legalPerson, report, new String[]{"id", "bytes"});

		return reportBean.save(report).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public Report read(@PathVariable("id") Long id) {
		return reportBean.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		reportBean.remove(reportBean.load(id));
	}


	@RequestMapping(value = "/upload-definition", headers = "Accept=*/*", produces = "application/json", method = RequestMethod.POST)
	@Transactional
	@ResponseBody
	public Report uploadReportDefinition(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {
		Report report;
		if (id == null) {
			report = new Report();
		} else {
			report = reportBean.load(id);
		}
		return reportBean.save(report).clone();
	}

	@RequestMapping(value = "/generate")
	@Transactional
	public ResponseEntity<byte[]> generate(@PathVariable("id") Long reportId, @RequestParam("json") String json, @RequestParam("format") ReportOutputFormat format) throws ReportManagerException {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		Report report = reportBean.load(reportId);
		headers.setContentDispositionFormData(report.getName(), report.getName() + "_" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime()) + "." + format.toString().toLowerCase());
		return new ResponseEntity<>(reportBean.generate(report, format, json), headers, HttpStatus.CREATED);
	}
}
