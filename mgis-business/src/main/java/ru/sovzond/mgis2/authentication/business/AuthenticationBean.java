package ru.sovzond.mgis2.authentication.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.sovzond.mgis2.authentication.dataaccess.IPrivilegeDAO;
import ru.sovzond.mgis2.authentication.dataaccess.IUserDAO;
import ru.sovzond.mgis2.authentication.model.User;

@Service
public class AuthenticationBean {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IPrivilegeDAO privilegeDAO;

	public void authenticate(String username, String password) {

	}

	public User findUserByName(String username) {
		return userDAO.findByName(username);
	}
}
