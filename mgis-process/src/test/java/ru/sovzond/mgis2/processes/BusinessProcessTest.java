package ru.sovzond.mgis2.processes;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BusinessProcessConfiguration.class)
public class BusinessProcessTest extends ProcessEngineTestCase {

	@Autowired
	private SpringProcessEngineConfiguration processEngineConfiguration;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private BusinessProcessConfiguration configuration;

	@Autowired
	private CalculateInterestService calculateInterestService;

	@Test
	@Deployment(resources = { "loanApproval.bpmn" })
	public void testProcessInvocation() {
		runtimeService.startProcessInstanceByKey("loanApproval");
	}

	@Test
	@Deployment(resources = { "newDiagram_1.bpmn" })
	public void testNewDiagram1_scriptTask() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("inputArray", new Integer[] { 5, 23, 42 });
		runtimeService.startProcessInstanceByKey("process1", variables);
	}
}
