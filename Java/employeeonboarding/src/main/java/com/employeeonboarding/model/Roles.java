package com.employeeonboarding.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Roles {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> users;

	public Roles() {
	}

	public Roles(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	
}
