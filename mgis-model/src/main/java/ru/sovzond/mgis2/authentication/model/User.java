package ru.sovzond.mgis2.authentication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "mgis2_user")
public class User implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column()
	private String password;

	@Column
	private boolean active;

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "mgis2_user_privilege", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id"))
	private List<Privilege> privileges = new ArrayList<Privilege>();

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(name = "mgis2_user_groups", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private List<Group> groups = new ArrayList<Group>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Privilege> getPrivileges() {
		// return Collections.unmodifiableList(privileges);
		return privileges;

	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public User clone() {
		User user = new User();
		user.setId(id);
		user.setActive(active);
		user.setUsername(username);
		user.setPassword(password);
		user.setPrivileges(privileges.stream().map(privilege -> {
			Privilege privilege1 = new Privilege();
			privilege1.setId(privilege.getId());
			privilege1.setName(privilege.getName());
			return privilege1;
		}).collect(Collectors.toList()));
		user.setGroups(groups.stream().map(group -> {
			Group group1 = new Group();
			group1.setId(group.getId());
			group1.setGroupname(group.getGroupname());
			group1.setPrivileges(group.getPrivileges().stream().map(privilege -> {
				Privilege privilege1 = new Privilege();
				privilege1.setId(privilege.getId());
				privilege1.setName(privilege.getName());
				return privilege;
			}).collect(Collectors.toList()));
			return group1;
		}).collect(Collectors.toList()));
		return user;
	}
}
