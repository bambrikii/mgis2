package ru.sovzond.mgis2.web.authentication;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
	private String username;
	private String password;
	private boolean active;

	private List<String> grantedAuthorities = new ArrayList<String>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(List<String> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
