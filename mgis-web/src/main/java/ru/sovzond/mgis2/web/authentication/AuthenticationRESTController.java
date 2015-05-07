package ru.sovzond.mgis2.web.authentication;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.sovzond.mgis2.authentication.model.User;

@RestController
@RequestMapping("/rest/auth")
@Scope("session")
// @ResponseBody
public class AuthenticationRESTController implements Serializable {

	private static final long serialVersionUID = 5197515604157373242L;

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "privileges", method = { RequestMethod.GET, RequestMethod.POST }, produces = { "application/json" }, headers = "Accept=application/json", consumes = { "application/json" })
	@ResponseBody
	public List<String> privileges() {
		Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> privileges = grantedAuthorities.stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());
		return privileges;
	}

	@RequestMapping(value = "users", method = { RequestMethod.GET, RequestMethod.POST }, produces = { "application/json" }, headers = "Accept=application/json", consumes = { "application/json" })
	@ResponseBody
	public List<User> users() {
		return authenticationService.users();
	}

}
