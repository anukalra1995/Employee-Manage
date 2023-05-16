package com.employeeonboarding.service.serviceInterface;

import java.util.List;
import java.util.Optional;

import com.employeeonboarding.model.User;

public interface UserService {

	String save(User user);

    String findUsername(String username,String password);

	Optional<User> findById(Long id);

	User saveOrUpdate(User user);

	String deleteById(Long id);

	List<User> findAll();
	
	User findByUsername(String username);
}
