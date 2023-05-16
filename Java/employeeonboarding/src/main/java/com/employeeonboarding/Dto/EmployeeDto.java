package com.employeeonboarding.Dto;

import com.employeeonboarding.model.EmployeeData;

public class EmployeeDto {
	
	public EmployeeData empData;
	
	public String username;
	
	public String roles;

	public EmployeeData getEmployees() {
		return empData;
	}

	public void setEmployees(EmployeeData empData) {
		this.empData = empData;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "EmployeeDto [employees=" + empData + ", username=" + username + ", roles=" + roles + "]";
	}
	
	
}
