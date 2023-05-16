package com.employeeonboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeonboarding.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
