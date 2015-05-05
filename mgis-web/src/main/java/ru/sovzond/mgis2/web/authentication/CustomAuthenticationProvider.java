package ru.sovzond.mgis2.web.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private LoginService loginService;

	@Autowired
	public CustomAuthenticationProvider(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserModel user = loginService.findUserByUserName(name);
		if (user != null && user.getPassword().equals(password)) {
			List<GrantedAuthority> grantedAuths = loginService.buildGrantedAuthorities(user);
			return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), grantedAuths);
		} else {
			throw new BadCredentialsException("Unable to auth against third party systems");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
