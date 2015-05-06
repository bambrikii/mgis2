package ru.sovzond.mgis2.authentication.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mgis2_privilege")
public class Privilege {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(unique = true, nullable = false, updatable = false)
	private String name;

	@ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<User>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return Collections.unmodifiableList(users);
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
			user.addPrivilege(this);
		}
	}

	public void removeUser(User user) {
		if (users.contains(user)) {
			users.remove(user);
			user.removePrivilege(this);
		}
	}

}
