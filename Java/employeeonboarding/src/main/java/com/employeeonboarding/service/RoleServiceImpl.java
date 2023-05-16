package com.employeeonboarding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.employeeonboarding.model.Roles;
import com.employeeonboarding.repository.RoleRepository;
import com.employeeonboarding.service.serviceInterface.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Roles> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Optional<Roles> findById(Long id) {
		return roleRepository.findById(id);
	}
	
	@Override
	public Roles saveOrUpdate(Roles roles) {
		return roleRepository.saveAndFlush(roles);
		
	}

	@Override
	public Roles findByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(name);
	}
	
}
