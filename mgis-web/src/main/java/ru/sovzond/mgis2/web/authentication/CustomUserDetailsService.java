package ru.sovzond.mgis2.web.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private AuthenticationService loginService;

	@Autowired
	public CustomUserDetailsService(AuthenticationService loginService) {
		this.loginService = loginService;
	}

	@Override
	public UserDetails loadUserByUsername(String paramString) throws AuthenticationException {
		UserModel user = loginService.findUserByUserName(paramString);
		if (user != null) {
			UserDetails details = new User(user.getUsername(), user.getPassword(), user.isActive(), true, true, user.getGrantedAuthorities().size() > 0, loginService.buildGrantedAuthorities(user));
			return details;
		}
		return new User(paramString, "", new ArrayList<>());
	}

}
