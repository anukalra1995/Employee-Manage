package com.employeeonboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeonboarding.model.EmployeeData;

public interface EmployeeRepository extends JpaRepository<EmployeeData, Long> {

}
