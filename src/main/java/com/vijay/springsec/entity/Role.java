package com.vijay.springsec.entity;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
@Entity
public class Role extends AbstactEntity implements GrantedAuthority {
	private static final long serialVersionUID = -6853086319781563974L;
	private String name;
	@ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
	private Set<Users> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}
}
