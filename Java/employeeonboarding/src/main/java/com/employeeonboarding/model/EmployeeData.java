package com.employeeonboarding.model;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class EmployeeData {
	
	@Id
	@Column(name="empid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
    
	@Column(name="name" , nullable = false)
    String name;
    
	@Column(name = "emailId")
	String email;
	
	@Column(name="salary")
    double salary;
    
	@Column(name="age")
    int empAge;
    
	@Column(name="gender" )
    String gender;
    
	@Column(name="Department" )
    String dept;
    
	@Column(name="yrOfJoin")
    int yearOfJoining;

	@Column(name="personalEmail")
	String peremail;

	public EmployeeData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeData(int id, String name, String email, double salary, int empAge, String gender, String dept,
			int yearOfJoining, String peremail) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.salary = salary;
		this.empAge = empAge;
		this.gender = gender;
		this.dept = dept;
		this.yearOfJoining = yearOfJoining;
		this.peremail = peremail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}     
	
	public int getEmpAge() {
		return empAge;
	}

	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getYearOfJoining() {
		return yearOfJoining;
	}

	public void setYearOfJoining(int yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPeremail() {
		return peremail;
	}

	public void setPeremail(String peremail) {
		this.peremail = peremail;
	}

	@Override
	public String toString() {
		return "EmployeeData [id=" + id + ", name=" + name + ", email=" + email + ", salary=" + salary + ", empAge="
				+ empAge + ", gender=" + gender + ", dept=" + dept + ", yearOfJoining=" + yearOfJoining +", Personal Email=" + peremail+ "]";
	}
		
	
}
