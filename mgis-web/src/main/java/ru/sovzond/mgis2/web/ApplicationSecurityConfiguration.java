package ru.sovzond.mgis2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.sovzond.mgis2.web.authentication.CustomAuthenticationProvider;
import ru.sovzond.mgis2.web.authentication.CustomUserDetailsService;

/**
 * Created by Alexander Arakelyan on 28/11/15.
 */
@Configuration
@EnableWebSecurity
@ComponentScan({"ru.sovzond.mgis2"})
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http    //
				.authorizeRequests() //
				.antMatchers("/login*").access("permitAll()") //
				.antMatchers("/**").access("hasRole('ROLE_USER')") //
				.and().formLogin().loginPage("/login") //
				.defaultSuccessUrl("/mgis2").failureUrl("/login?failed").usernameParameter("username").passwordParameter("password") //
		;
		http.sessionManagement().invalidSessionUrl("/login").maximumSessions(1).expiredUrl("/login");
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies("JSESSIONID").invalidateHttpSession(true);
		http.rememberMe();
		http.csrf().disable();
		http.authenticationProvider(customAuthenticationProvider).userDetailsService(customUserDetailsService);
	}
}
