package ru.sovzond.mgis2.web.authentication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	private Map<String, UserModel> users = new HashMap<String, UserModel>();

	public LoginService() {
		createUserModel("asd", new String[] { "ROLE_USER" });
		createUserModel("qwe", new String[] { "ROLE_USER" });
		createUserModel("zxc", new String[] { "ROLE_USER" });
	}

	private UserModel createUserModel(String username, String[] authorities) {
		UserModel asd = new UserModel();
		asd.setUsername(username);
		asd.setPassword(username);
		asd.setGrantedAuthorities(Arrays.asList(authorities));
		users.put(asd.getUsername(), asd);
		return asd;
	}

	public UserModel findUserByUserName(String username) {
		return users.get(username);
	}

	public List<GrantedAuthority> buildGrantedAuthorities(UserModel user) {
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		for (String role : user.getGrantedAuthorities()) {
			grantedAuths.add(new SimpleGrantedAuthority(role));
		}
		return grantedAuths;
	}

}
