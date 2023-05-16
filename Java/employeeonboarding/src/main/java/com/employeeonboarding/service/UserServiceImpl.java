package com.employeeonboarding.service;

import java.util.*;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

import com.employeeonboarding.Constants.ConstantUtils;
import com.employeeonboarding.config.JwtTokenProvider;
import com.employeeonboarding.model.User;
import com.employeeonboarding.repository.RoleRepository;
import com.employeeonboarding.repository.UserRepository;
import com.employeeonboarding.service.serviceInterface.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	User users;
	
	@Override
	public String save(User user) {
		JSONObject jsonObject = new JSONObject();
		
		try {
			user.setPwd(new BCryptPasswordEncoder().encode(user.getPwd()));
			user.setRole(roleRepository.findByName(ConstantUtils.USER.toString()));
			User savedUser = userRepository.save(user);
			
			jsonObject.put("message", savedUser.getFname()+" "+savedUser.getLname() + " saved succesfully");
		}catch (JSONException e) {
			try {
				jsonObject.put("exception", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return jsonObject+ "";
		}
		return jsonObject+"";
	}
	
	@Override
	public String findUsername(String username, String password) {
		
		JSONObject jsonObject = new JSONObject();
		
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			if (authentication.isAuthenticated()) {
				
				users = findAll().stream().filter((x) -> x.getUsername().equals(username) ).findAny().get();
				
				jsonObject.put("name", users.getFname()+" "+users.getLname());
				jsonObject.put("authorities", authentication.getAuthorities());
				jsonObject.put("token", tokenProvider.createToken(users.getUsername(), users.getRole()));
				jsonObject.put("username", users.getUsername());
				
			}
		}catch (JSONException e) {
			try {
				jsonObject.put("exception", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return jsonObject+" ";
	}

	
	@Override
	public Optional<User> findById(Long id) {
		
		Optional<User> userById = findAll().stream().filter(x -> x.getId() == id).findFirst();
		return userById;
	}

	
	@Override
	public User saveOrUpdate(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public String deleteById(Long id) {
		String message = "";
		try {
			userRepository.deleteById(id);
			message = "User deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		
		users = findAll().stream().filter((x) -> x.getUsername().equals(username)).findAny().get();
		
		return users;
	}
}
