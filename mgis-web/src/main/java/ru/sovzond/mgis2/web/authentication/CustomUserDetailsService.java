package ru.sovzond.mgis2.web.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private LoginService loginService;

	@Autowired
	public CustomUserDetailsService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public UserDetails loadUserByUsername(String paramString) throws UsernameNotFoundException {
		UserModel user = loginService.findUserByUserName(paramString);
		if (user != null) {
			UserDetails details = new User(user.getUsername(), user.getPassword(), true, true, true, true, loginService.buildGrantedAuthorities(user));
			return details;
		}
		return null;
	}

}
