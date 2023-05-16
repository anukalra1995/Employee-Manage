package com.employeeonboarding.service.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.employeeonboarding.model.Roles;

public interface RoleService {

	List<Roles> findAll();

	Roles saveOrUpdate(Roles roles);

	Roles findByName(String string);
	
	Optional<Roles> findById(Long id);
	
}
