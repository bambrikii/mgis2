package ru.sovzond.mgis2.authentication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "mgis2_group")
public class Group implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false, updatable = false)
	private String groupname;

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "mgis2_group_privilege", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id"))
	private List<Privilege> privileges = new ArrayList<Privilege>();

	@ManyToOne(targetEntity = Group.class, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "parentgroup_id"/* , referencedColumnName = "id", table = "mgis2_group" */)
	private Group parentGroup;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentGroup", cascade = {CascadeType.PERSIST})
	private List<Group> childGroups = new ArrayList<Group>();

	@ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "groups")
	private List<User> users = new ArrayList<User>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Group getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(Group parentGroup) {
		this.parentGroup = parentGroup;
	}

	public List<Group> getChildGroups() {
		return childGroups;
	}

	public void setChildGroups(List<Group> childGroups) {
		this.childGroups = childGroups;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Group clone() {
		Group group = new Group();
		group.setId(id);
		group.setGroupname(groupname);
		group.setParentGroup(parentGroup != null ? parentGroup.clone() : null);
		group.setPrivileges(privileges.stream().map(privilege -> privilege.clone()).collect(Collectors.toList()));
		group.setUsers(users.stream().map(user -> {
			User user1 = new User();
			user1.setId(user.getId());
			user1.setUsername(user.getUsername());
			return user1;
		}).collect(Collectors.toList()));
		return group;
	}
}
