package ru.sovzond.mgis2.processes;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CalculateInterestService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution delegate) throws Exception {
		System.out.println("Spring Bean invoked.");
	}

}