package ru.sovzond.mgis2.web.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.authentication.business.AuthenticationBean;
import ru.sovzond.mgis2.authentication.model.Privilege;
import ru.sovzond.mgis2.authentication.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationBean authenticationBean;

	@Transactional
	public UserModel findUserByUserName(String username) {
		User user = authenticationBean.findUserByName(username);
		if (user != null) {
			UserModel userModel = new UserModel();
			userModel.setUsername(user.getUsername());
			userModel.setPassword(user.getPassword());
			userModel.setActive(user.isActive());
			List<String> grantedAuthorities = user.getPrivileges().stream().map(privilege -> privilege.getName()).collect(Collectors.toList());
			userModel.setGrantedAuthorities(grantedAuthorities);
			return userModel;
		}
		return null;
	}

	@Transactional
	public List<GrantedAuthority> buildGrantedAuthorities(UserModel userModel) {
		String username = userModel.getUsername();
		List<Privilege> privileges = authenticationBean.loadPrivileges(username);
		List<GrantedAuthority> grantedAuthorities = privileges.stream().map(privilege -> {
			return new SimpleGrantedAuthority(privilege.getName());
		}).collect(Collectors.toList());
		return grantedAuthorities;
	}

	@Transactional
	public List<String> privileges() {
		Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> privileges = grantedAuthorities.stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());
		return privileges;
	}

	@Transactional
	public List<User> users() {
		return authenticationBean.findUsers();
	}
}
