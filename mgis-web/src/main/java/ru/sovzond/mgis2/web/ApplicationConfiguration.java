package ru.sovzond.mgis2.web;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.sovzond.mgis2.web.resolvers.JsonViewResolver;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan({"ru.sovzond.mgis2"})
@PropertySource(value = {"classpath:application.properties"})
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment environment;

	/*
	 * Configure ContentNegotiationManager
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[]{"ru.sovzond.mgis2"});
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
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		// Define all possible view resolvers
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

		// resolvers.add(jaxb2MarshallingXmlViewResolver());
		resolvers.add(jsonViewResolver());
		resolvers.add(jspViewResolver());
		// resolvers.add(pdfViewResolver());
		// resolvers.add(excelViewResolver());

		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	/*
	 * Configure View resolver to provide XML output Uses JAXB2 marshaller to marshall/unmarshall POJO's (with JAXB annotations) to XML
	 */
	// @Bean
	// public ViewResolver jaxb2MarshallingXmlViewResolver() {
	// Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	// // marshaller.setClassesToBeBound(Pizza.class);
	// return new Jaxb2MarshallingXmlViewResolver(marshaller);
	// }

	/*
	 * Configure View resolver to provide JSON output using JACKSON library to convert object in JSON format.
	 */
	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	/*
	 * Configure View resolver to provide PDF output using lowagie pdf library to generate PDF output for an object content
	 */
	// @Bean
	// public ViewResolver pdfViewResolver() {
	// return new PdfViewResolver();
	// }

	/*
	 * Configure View resolver to provide XLS output using Apache POI library to generate XLS output for an object content
	 */
	// @Bean
	// public ViewResolver excelViewResolver() {
	// return new ExcelViewResolver();
	// }

	/*
	 * Configure View resolver to provide HTML output This is the default format in absence of any type suffix.
	 */
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(500000);
		return multipartResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/app2/**").addResourceLocations("/app2/");
		registry.addResourceHandler("/template/**").addResourceLocations("/template/");
		registry.addResourceHandler("/bower_components/**").addResourceLocations("/bower_components/");
	}
}
