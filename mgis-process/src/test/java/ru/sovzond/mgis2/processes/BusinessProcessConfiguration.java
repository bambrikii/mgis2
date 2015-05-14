package ru.sovzond.mgis2.processes;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "ru.sovzond.mgis2" })
@PropertySource(value = { "classpath:application.properties" })
public class BusinessProcessConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "ru.sovzond.mgis2" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	@Bean
	public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, HibernateTransactionManager transactionManager,
			CalculateInterestService calculateInterestService) {
		SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
		configuration.setProcessEngineName("engine");
		configuration.setDataSource(dataSource);
		configuration.setTransactionManager(transactionManager);
		configuration.setDatabaseSchemaUpdate("true");
		configuration.setJobExecutorActivate(false);
		configuration.setDeploymentResources(new Resource[] { new ClassPathResource("loanApproval.bpmn") });
		Map<Object, Object> beans = new HashMap<Object, Object>();
		beans.put("calculateInterestService", calculateInterestService);
		configuration.setBeans(beans);
		configuration.buildProcessEngine();
		return configuration;
	}

	@Bean
	public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration processEngineConfiguration) {
		ProcessEngineFactoryBean processEngineFactory = new ProcessEngineFactoryBean();
		processEngineFactory.setProcessEngineConfiguration(processEngineConfiguration);
		return processEngineFactory;
	}

	@Bean
	public RepositoryService repositoryService(ProcessEngineFactoryBean processEngineFactoryBean) throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration().getRepositoryService();
	}

	@Bean
	public RuntimeService runtimeService(ProcessEngineFactoryBean processEngineFactoryBean) throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration().getRuntimeService();
	}

	@Bean
	public ManagementService managementService(ProcessEngineFactoryBean processEngineFactoryBean) throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration().getManagementService();
	}

	@Bean
	public TaskService taskService(ProcessEngineFactoryBean processEngineFactoryBean) throws Exception {
		return processEngineFactoryBean.getProcessEngineConfiguration().getTaskService();
	}
}