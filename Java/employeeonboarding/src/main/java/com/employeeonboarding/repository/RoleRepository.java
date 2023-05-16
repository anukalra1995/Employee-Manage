package com.employeeonboarding.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employeeonboarding.model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
	
	Roles findByName(@Param("name") String name);
	
}
