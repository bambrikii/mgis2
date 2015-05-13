package ru.sovzond.mgis2.processes;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.EmbeddedProcessApplication;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessApplication
// @Service
public class BusinessProcessStarter extends EmbeddedProcessApplication implements InitializingBean {

	@Autowired
	private RuntimeService runtimeService;

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		runtimeService.startProcessInstanceByKey("loanApproval");
	}

}
