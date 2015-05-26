package ru.sovzond.mgis2.web_test;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Scope("request")
public class TestRestController {

	@Autowired
	private RuntimeService runtimeService;

	@RequestMapping(value = "/loanApplication", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String loanApplication() {
		runtimeService.startProcessInstanceByKey("loanApproval");
		return "ok";
	}
}
