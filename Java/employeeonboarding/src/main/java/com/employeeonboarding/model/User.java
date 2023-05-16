package com.employeeonboarding.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

	@Id
	@Column(name="userid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="firstName" )
	private String fname;
	
	@Column(name="lastName")
	private String lname;
	
	@Column(name="username", nullable = false, unique = true)
	private String username;
	
	@Column(name="pwd", nullable = false)
	private String pwd;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Roles role;
    
	public User() {
		super();
	}

	public User(long id, String fname, String lname, String username, String pwd, Roles role) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.pwd = pwd;
		this.role = role;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", username=" + username + ", pwd=" + pwd
				+ ", roles=" + role + "]";
	}
	
	
}
