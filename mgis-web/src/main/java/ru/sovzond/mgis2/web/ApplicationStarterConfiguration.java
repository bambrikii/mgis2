package ru.sovzond.mgis2.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import ru.sovzond.mgis2.web.proxy.ProxyServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
@EnableTransactionManagement
@ComponentScan({"ru.sovzond.mgis2"})
@PropertySource(value = {"classpath:application.properties"})
public class ApplicationStarterConfiguration implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Create the 'root' Spring application context

		// Manage the lifecycle of the root application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationWebMvcConfiguration.class);
		rootContext.register(ApplicationSecurityConfiguration.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));

		//		MultipartConfigElement multipartConfigElement = new MultipartConfigElement("./temp", 1024 * 1024 * 10, 1024 * 1024 * 25, 1024 * 1024 * 1);

		// Create the dispatcher servlet's Spring application context
		ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher-servlet", new DispatcherServlet(rootContext));
		dispatcherServlet.addMapping("/");
		dispatcherServlet.addMapping("/login");
		dispatcherServlet.addMapping("/logout");
		dispatcherServlet.addMapping("/mgis2");
		dispatcherServlet.addMapping("/rest/*");
		dispatcherServlet.addMapping("/app2");
		dispatcherServlet.addMapping("/app2");
		dispatcherServlet.addMapping("/template");
		dispatcherServlet.addMapping("/bower_components");


		ServletRegistration.Dynamic proxyServlet = servletContext.addServlet("proxy-servlet", new ProxyServlet());
		proxyServlet.addMapping("/proxy");

		//		ServletRegistration.Dynamic kpt2landsImportServlet = servletContext.addServlet("kpt-to-lands-import-servlet", new LandsImportServlet());
		//		kpt2landsImportServlet.setMultipartConfig(multipartConfigElement);
		//		kpt2landsImportServlet.addMapping("/data-exchange/import/rusregister/lands");

		FilterRegistration.Dynamic delegatingFilter = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
		delegatingFilter.addMappingForUrlPatterns(null, false, "/");
		delegatingFilter.addMappingForUrlPatterns(null, false, "/login");
		delegatingFilter.addMappingForUrlPatterns(null, false, "/logout");
		delegatingFilter.addMappingForUrlPatterns(null, false, "/mgis2");
		delegatingFilter.addMappingForUrlPatterns(null, false, "/logout");
		delegatingFilter.addMappingForUrlPatterns(null, false, "/rest/*");

		FilterRegistration hibernateFilter = servletContext.addFilter("hibernate-filter", new OpenSessionInViewFilter());
		hibernateFilter.addMappingForUrlPatterns(null, false, "/*");

		FilterRegistration expiredFilter = servletContext.addFilter("expired-filter", new ExpiredSessionFilter());
		expiredFilter.addMappingForUrlPatterns(null, false, "/rest/*");

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		FilterRegistration encodingFilter = servletContext.addFilter("encoding-filter", characterEncodingFilter);
		encodingFilter.addMappingForUrlPatterns(null, false, "/rest/*");

	}

}
