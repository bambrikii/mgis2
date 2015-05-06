package ru.sovzond.mgis2.web.authentication;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class PrivilegesController {
	@RequestMapping("/privileges")
	public List<String> privileges() {
		Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> privileges = grantedAuthorities.stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());
		return privileges;
	}
}
