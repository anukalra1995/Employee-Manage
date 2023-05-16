package com.employeeonboarding.service.serviceInterface;

import java.util.*;

import com.employeeonboarding.model.EmployeeData;

public interface EmployeeService {
	
	String save(EmployeeData employee);

	Optional<EmployeeData> findById(Long id);

	EmployeeData saveOrUpdate(EmployeeData EmployeeData);

	String deleteById(Long id);

	List<EmployeeData> findAll();
	
	public EmployeeData findByEmail(String username);
}
