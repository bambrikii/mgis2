package ru.sovzond.mgis2.processes;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BusinessProcessConfiguration.class)
public class BusinessProcessTest {

	// @Autowired
	// private BusinessProcessStarter businessProcessStarter;

	@Autowired
	private SpringProcessEngineConfiguration processEngineConfiguration;

	// @Rule
	// public ProcessEngineRule processEngineRule = new ProcessEngineRule(processEngineConfiguration.buildProcessEngine()/*
	// * createProcessEngineProgramatically(
	// * )
	// */);

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private BusinessProcessConfiguration configuration;

	@Autowired
	private CalculateInterestService calculateInterestService;

	// @Autowired
	// private BusinessProcessStarter businessProcessStarter;

	@Test
	// @Deployment(resources = { "loanApproval.bpmn" })
	public void testProcessInvocation() {
		// ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		// RuntimeService runtimeService = processEngineRule.getRuntimeService();
		// TaskService taskService = processEngineRule.getTaskService();
		// runtimeService.get
		// ProcessEngine processEngine = BpmPlatform.getDefaultProcessEngine();
		// ProcessEngine processEngine2 = ProcessEngines.getDefaultProcessEngine();
		// EmbeddedProcessApplication processApp = new EmbeddedProcessApplication();
		// processApp.createDeployment("processes.xml", new DeploymentBuilderImpl((RepositoryServiceImpl) repositoryService));
		// processApp.deploy();
		// Map<String, Object> variables = new HashMap<String, Object>();
		// variables.put("calculateInterestService", calculateInterestService);
		runtimeService.startProcessInstanceByKey("loanApproval"/* , variables */);
		// System.out.println(businessProcessStarter);
		// ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		// ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
		// .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)//.setJdbcUrl("jdbc:postgresql:localhost:5432/mgis2")
		// .setJobExecutorActivate(true).buildProcessEngine();
	}

	// protected ProcessEngine createProcessEngineProgramatically() {
	// SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
	// processEngineConfiguration.setCustomPostBPMNParseListeners(Arrays.asList(new BpmnParseListener[] { new FoxFailedJobParseListener() }));
	// return processEngineConfiguration.buildProcessEngine();
	// }
}
